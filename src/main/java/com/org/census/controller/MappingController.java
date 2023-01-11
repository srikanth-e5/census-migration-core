package com.org.census.controller;

import com.org.census.model.MappingResponseDto;
import com.org.census.service.MappingService;
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
