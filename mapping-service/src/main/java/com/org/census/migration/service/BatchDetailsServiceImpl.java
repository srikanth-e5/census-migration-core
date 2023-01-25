package com.org.census.migration.service;

import com.amazonaws.services.s3.AmazonS3;
import com.org.census.migration.exception.ValidationException;
import com.org.census.migration.mapper.ModelEntityMapper;
import com.org.census.migration.model.BatchDetails;
import com.org.census.migration.model.BatchDetailsDto;
import com.org.census.migration.model.EHRMapping;
import com.org.census.migration.model.Processes;
import com.org.census.migration.repository.BatchDetailsRepository;
import com.org.census.migration.repository.EHRMappingRepository;
import com.org.census.migration.utils.S3Utils;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class BatchDetailsServiceImpl implements BatchDetailsService{

    @Autowired
    BatchDetailsRepository batchDetailsRepository;

    @Autowired
    ModelEntityMapper modelEntityMapper;

    @Autowired
    EHRMappingRepository ehrMappingRepository;

    @Autowired
    AmazonS3 s3Client;

    @Value("${com.org.census.migration.s3.bucketName:source-files}")
    private String s3BucketName;

    @Override
    public void saveBatchDetails(BatchDetailsDto batchDetailsDto, List<MultipartFile> patientCreationFiles) {
        if(patientCreationFiles != null){
            S3Utils.uploadFile(s3Client,s3BucketName,patientCreationFiles);
        }
        BatchDetails batchDetails = modelEntityMapper.toBatchDetailsEntity(batchDetailsDto);
        for(Processes processes : batchDetails.getProcessesList())
            processes.setBatchDetails(batchDetails);
        BatchDetails batchNameDetails = batchDetailsRepository.findFirstBySourceEhrNameAndTargetEhrNameAndServiceLineAndClientNameOrderByBatchNameDesc(
                batchDetails.getSourceEhrName(),batchDetails.getTargetEhrName(),batchDetails.getServiceLine(),
                batchDetails.getClientName()
        );
        if(Objects.isNull(batchNameDetails)) {
            batchDetails.setBatchName("Batch_1");
        } else {
            int batchNumber = Integer.parseInt(batchNameDetails.getBatchName().split("_")[1]) + 1;
            batchDetails.setBatchName("Batch_" + batchNumber);
        }
        batchDetailsRepository.save(batchDetails);
    }

    @Override
    public BatchDetailsDto getBatchDetails(String sourceEHRName, String targetEHRName, String serviceLine,
                                           String clientName, String batchName) {
        BatchDetails batchDetails = batchDetailsRepository.findBySourceEhrNameAndTargetEhrNameAndServiceLineAndClientNameAndBatchName(
                sourceEHRName, targetEHRName, serviceLine, clientName, batchName);
        return modelEntityMapper.toBatchDetailsDTO(batchDetails);
    }

    @Override
    public List<BatchDetailsDto> getBatchDetailsList(String sourceEHRName, String targetEHRName, String serviceLine,
                                                     String clientName) {
        List<BatchDetails> batchDetailsList = batchDetailsRepository.findBySourceEhrNameAndTargetEhrNameAndServiceLineAndClientName(
                                                sourceEHRName, targetEHRName, serviceLine, clientName);
        return modelEntityMapper.toBatchDetailsDTOList(batchDetailsList);
    }

    @Override
    public boolean uploadFileValidation(String sourceEHRName, String targetEHRName, String serviceLine,
                                        String clientName, String targetProcessName,
                                        MultipartFile file) throws IOException {
        String fileName = file.getName().toUpperCase();
        boolean extension = fileName.endsWith(".XLSX") || fileName.endsWith(".XLS") ||
                            fileName.endsWith(".GSHEET") || fileName.endsWith(".CSV");
        if(extension) {
            List<String> sheetNameList = getSheetNames(file.getInputStream());
            List<EHRMapping> ehrMappingList = ehrMappingRepository.findBySourceEHRNameAndTargetEHRNameAndServiceLineAndClientNameAndTargetProcessName(
                    sourceEHRName, targetEHRName, serviceLine, clientName, targetProcessName);
            List<String> fileNames = ehrMappingList.stream().map(EHRMapping::getSourceFileName).distinct().collect(Collectors.toList());
            List<String> sheetNames = ehrMappingList.stream().map(EHRMapping::getSourceSheetName).distinct().collect(Collectors.toList());
            for (String fileNameIterator : fileNames) {
                if(!fileName.startsWith(fileNameIterator))
                    throw new ValidationException("File not supported");
            }
            for (String sheetNameIterator : sheetNameList) {
                if(!sheetNames.contains(sheetNameIterator))
                    throw new ValidationException("File not supported");
            }
        } else {
            throw new ValidationException("File not supported");
        }
        return true;
    }

    public List<String> getSheetNames(InputStream inputStream){
        try {
            Workbook workbook = new XSSFWorkbook(inputStream);
            int sheetCount = workbook.getNumberOfSheets();
            List<String> sheetNames = new ArrayList<>();
            for(int i=1; i<=sheetCount;i++){
                sheetNames.add(workbook.getSheetName(i));
            }
            return sheetNames;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
        }
    }
}
