package com.org.census.migration.service;

import com.org.census.migration.model.CreateMappingRequestDto;
import com.org.census.migration.model.MappingResponseDto;
import com.org.census.migration.model.UpdateMappingRequestDto;

import java.util.List;

public interface MappingService {

    MappingResponseDto getMappingDetails(String sourceEHRName, String targetEHRName, String serviceLine,
                                         String clientName);

    void saveOneTimeMappingDetails(String sourceEHRName, String targetEHRName, String serviceLine, List<CreateMappingRequestDto> createMappingRequestDto);

    void updateOneTimeMappingDetails(String sourceEHRName, String targetEHRName, String serviceLine, List<UpdateMappingRequestDto> mappingRequestDto);

    void saveClientMappingDetails(String clientName, String sourceEHRName, String targetEHRName, String serviceLine);

    void updateClientMappingDetails(String clientName, String sourceEHRName, String targetEHRName, String serviceLine, List<UpdateMappingRequestDto> mappingRequestDto);

}
