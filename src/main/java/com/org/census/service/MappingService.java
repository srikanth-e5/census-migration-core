package com.org.census.service;

import com.org.census.model.MappingResponseDto;

public interface MappingService {

    MappingResponseDto getOneTimeMappingDetails(String sourceEHRName, String targetEHRName, String serviceLine,
                                                String clientName);

}
