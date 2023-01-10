package com.org.census.service;

import com.org.census.dto.MappingResponseDto;
import org.springframework.stereotype.Service;

@Service
public class MappingServiceImpl implements MappingService {

    @Override
    public MappingResponseDto getOneTimeMappingDetails(String ehrType, String targetEHRType, String sourceEHRType) {
        MappingResponseDto mappingResponseDto = new MappingResponseDto();
        return mappingResponseDto;
    }
}
