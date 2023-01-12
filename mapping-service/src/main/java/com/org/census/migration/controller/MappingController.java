package com.org.census.migration.controller;

import com.org.census.migration.service.MappingService;
import com.org.census.migration.model.MappingResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MappingController implements MappingApi {

    @Autowired
    private MappingService mappingService;

    @Override
    public MappingResponseDto getOneTimeMappingDetails(String sourceEHRName, String targetEHRName, String serviceLine,
                                                       String clientName) {
        return mappingService.getOneTimeMappingDetails(sourceEHRName, targetEHRName, serviceLine, clientName);
    }
}
