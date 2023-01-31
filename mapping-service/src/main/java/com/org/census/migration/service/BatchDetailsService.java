package com.org.census.migration.service;

import com.org.census.migration.model.BatchDetailsDto;
import com.org.census.migration.model.BatchDetailsRequestDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

public interface BatchDetailsService {
    void saveBatchDetails(BatchDetailsRequestDto batchDetailsRequestDto);

    BatchDetailsDto getBatchDetails(String sourceEHRName, String targetEHRName,
                                    String serviceLine, String clientName, String batchName);

    List<BatchDetailsDto> getBatchDetailsList(String sourceEHRName, String targetEHRName,
                                              String serviceLine, String clientName);

    boolean uploadFileValidation(String sourceEHRName, String targetEHRName, String serviceLine,
                                 String clientName, String batchName, String processName,
                                 MultipartFile file) throws IOException;
}
