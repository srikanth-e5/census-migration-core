package com.org.census.migration.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.org.census.migration.exception.ValidationException;
import com.org.census.migration.model.BatchDetailsDto;
import com.org.census.migration.service.BatchDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
public class BatchDetailsController implements BatchDetailsApi{

    @Autowired
    BatchDetailsService batchDetailsService;

    @Override
    public ResponseEntity<Void> saveBatchDetails(String batchDetails, List<MultipartFile> patientCreationFiles) {
        BatchDetailsDto batchDetailsDto;
        try{
            batchDetailsDto = new ObjectMapper().readValue(batchDetails, BatchDetailsDto.class);
        }catch (IOException exception){
            throw new ValidationException("Invalid Data");
        }
        batchDetailsService.saveBatchDetails(batchDetailsDto, patientCreationFiles);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Override
    public BatchDetailsDto getBatchDetails(String sourceEHRName, String targetEHRName, String serviceLine,
                                           String clientName, String batchName) {
        return batchDetailsService.getBatchDetails(sourceEHRName, targetEHRName, serviceLine, clientName, batchName);
    }

    @Override
    public List<BatchDetailsDto> getBatchDetailsList(String sourceEHRName, String targetEHRName, String serviceLine,
                                                     String clientName) {
        return batchDetailsService.getBatchDetailsList(sourceEHRName, targetEHRName, serviceLine, clientName);
    }

    @Override
    public boolean uploadFileValidation(String sourceEHRName, String targetEHRName, String serviceLine,
                                                        String clientName, String targetProcessName,
                                                        MultipartFile file) throws IOException {
        return batchDetailsService.uploadFileValidation(sourceEHRName, targetEHRName, serviceLine,
                                                                             clientName, targetProcessName, file);
    }
}
