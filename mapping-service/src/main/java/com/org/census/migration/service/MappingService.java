package com.org.census.migration.service;

import com.org.census.migration.model.MappingResponseDto;

public interface MappingService {

    MappingResponseDto getOneTimeMappingDetails(String sourceEHRName, String targetEHRName, String serviceLine,
                                                String clientName);

}
