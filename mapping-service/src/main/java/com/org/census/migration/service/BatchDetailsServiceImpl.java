package com.org.census.migration.service;

import com.org.census.migration.mapper.ModelEntityMapper;
import com.org.census.migration.model.BatchDetails;
import com.org.census.migration.model.BatchDetailsDto;
import com.org.census.migration.model.Processes;
import com.org.census.migration.repository.BatchDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class BatchDetailsServiceImpl implements BatchDetailsService{

    @Autowired
    BatchDetailsRepository batchDetailsRepository;

    @Autowired
    ModelEntityMapper modelEntityMapper;

    @Override
    public void saveBatchDetails(BatchDetailsDto batchDetailsDto) {
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
}
