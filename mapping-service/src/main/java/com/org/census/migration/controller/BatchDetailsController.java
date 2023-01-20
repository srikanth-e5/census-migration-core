package com.org.census.migration.controller;

import com.org.census.migration.model.BatchDetailsDto;
import com.org.census.migration.service.BatchDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BatchDetailsController implements BatchDetailsApi{

    @Autowired
    BatchDetailsService batchDetailsService;

    @Override
    public ResponseEntity<Void> saveBatchDetails(BatchDetailsDto batchDetailsDto) {
        batchDetailsService.saveBatchDetails(batchDetailsDto);
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
}
