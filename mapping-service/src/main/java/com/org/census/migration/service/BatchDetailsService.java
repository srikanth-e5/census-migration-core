package com.org.census.migration.service;

import com.org.census.migration.model.BatchDetailsDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

import static com.org.census.migration.constant.Constants.ApiPath.CLIENT_NAME;
import static com.org.census.migration.constant.Constants.ApiPath.SOURCE_TARGET_EHR_SERVICE_LINE;

public interface BatchDetailsService {
    void saveBatchDetails(BatchDetailsDto batchDetailsDto);

    BatchDetailsDto getBatchDetails(String sourceEHRName, String targetEHRName,
                                    String serviceLine, String clientName, String batchName);

    List<BatchDetailsDto> getBatchDetailsList(String sourceEHRName, String targetEHRName,
                                              String serviceLine, String clientName);
}
