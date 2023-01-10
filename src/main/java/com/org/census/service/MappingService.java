package com.org.census.service;

import com.org.census.dto.MappingResponseDto;

public interface MappingService {

    MappingResponseDto getOneTimeMappingDetails(String ehrType, String targetEHRType, String sourceEHRType);

}
