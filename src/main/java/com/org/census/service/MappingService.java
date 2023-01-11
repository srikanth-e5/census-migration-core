package com.org.census.service;

import com.org.census.model.MappingResponseDto;

public interface MappingService {

    MappingResponseDto getOneTimeMappingDetails(String sourceEHRType, String targetEHRType, String serviceLine);

}
