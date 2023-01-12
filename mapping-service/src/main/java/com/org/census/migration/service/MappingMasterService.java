package com.org.census.migration.service;

import com.org.census.migration.model.MappingMasterDto;
import com.org.census.migration.model.MappingMasterResponseDto;

import java.util.List;

public interface MappingMasterService {
    void saveMappingMaster(String sourceEHRName, String targetEHRName, String serviceLine,
                                               String clientName, String masterType,
                                               List<MappingMasterDto> mappingMasterDto);

    MappingMasterResponseDto getMappingMaster(String sourceEHRName, String targetEHRName, String serviceLine,
                                              String clientName, String masterType);

    void updateMappingMaster(String sourceEHRName, String targetEHRName, String serviceLine,
                                               String clientName, String masterType,
                                               List<MappingMasterDto> mappingMasterDto);

    void deleteMappingMaster(String sourceEHRName, String targetEHRName, String serviceLine,
                                                 String clientName, String masterType, String sourceValue);
}
