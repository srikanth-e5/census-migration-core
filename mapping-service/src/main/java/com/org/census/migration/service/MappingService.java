package com.org.census.migration.service;

import com.org.census.migration.model.MappingRequestDto;
import com.org.census.migration.model.MappingResponseDto;

import java.util.List;

public interface MappingService {

    MappingResponseDto getMappingDetails(String sourceEHRName, String targetEHRName, String serviceLine,
                                         String clientName);

    void saveOneTimeMappingDetails(String sourceEHRName, String targetEHRName, String serviceLine, List<MappingRequestDto> mappingRequestDto);

    void saveClientMappingDetails(String clientName, String sourceEHRName, String targetEHRName, String serviceLine);

    void updateOneTimeMappingDetails(String sourceEHRName, String targetEHRName, String serviceLine, List<MappingRequestDto> mappingRequestDto);
}
