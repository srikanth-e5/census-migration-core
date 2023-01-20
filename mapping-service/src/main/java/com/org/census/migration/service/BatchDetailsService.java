package com.org.census.migration.service;

import com.org.census.migration.model.BatchDetailsDto;

public interface BatchDetailsService {
    void saveBatchDetails(BatchDetailsDto batchDetailsDto);
}
