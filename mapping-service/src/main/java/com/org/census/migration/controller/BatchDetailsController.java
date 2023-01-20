package com.org.census.migration.controller;

import com.org.census.migration.model.BatchDetailsDto;
import com.org.census.migration.service.BatchDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BatchDetailsController implements BatchDetailsApi{

    @Autowired
    BatchDetailsService batchDetailsService;

    @Override
    public ResponseEntity<Void> saveBatchDetails(BatchDetailsDto batchDetailsDto) {
        batchDetailsService.saveBatchDetails(batchDetailsDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
