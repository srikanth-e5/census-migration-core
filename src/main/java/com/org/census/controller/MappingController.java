package com.org.census.controller;

import com.org.census.dto.MappingResponseDto;
import com.org.census.service.MappingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MappingController implements MappingApi {

    @Autowired
    private MappingService mappingService;

    @Override
    public MappingResponseDto getOneTimeMappingDetails(String sourceEHRType, String targetEHRType, String serviceLine) {
        return mappingService.getOneTimeMappingDetails(sourceEHRType, targetEHRType, serviceLine);
    }
}
