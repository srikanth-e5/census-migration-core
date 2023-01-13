package com.org.census.migration.service;

import com.org.census.migration.dto.MappingRequestDto;
import com.org.census.migration.dto.MappingResponseDto;

import java.util.List;

public interface MappingService {

    MappingResponseDto getMappingDetails(String sourceEHRName, String targetEHRName, String serviceLine,
                                         String clientName);

    void saveOneTimeMappingDetails(String sourceEHRName, String targetEHRName, String serviceLine, List<MappingRequestDto> mappingRequestDto);
}
