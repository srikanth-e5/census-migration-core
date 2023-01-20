package com.org.census.migration.service;

import com.org.census.migration.mapper.ModelEntityMapper;
import com.org.census.migration.model.BatchDetails;
import com.org.census.migration.model.BatchDetailsDto;
import com.org.census.migration.model.Processes;
import com.org.census.migration.repository.BatchDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        batchDetailsRepository.save(batchDetails);
    }
}
