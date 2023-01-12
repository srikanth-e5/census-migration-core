package com.org.census.migration.controller;

import com.org.census.migration.model.MappingRequestDto;
import com.org.census.migration.model.MappingResponseDto;
import com.org.census.migration.service.MappingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MappingController implements MappingApi {

    @Autowired
    private MappingService mappingService;

    @Override
    public MappingResponseDto getMappingDetails(String sourceEHRName, String targetEHRName, String serviceLine,
                                                String clientName) {
        return mappingService.getMappingDetails(sourceEHRName, targetEHRName, serviceLine, clientName);
    }

    @Override
    public ResponseEntity<Void> saveOneTimeMappingDetails(String sourceEHRName, String targetEHRName,
                                                          String serviceLine,
                                                          List<MappingRequestDto> mappingRequestDto) {
        mappingService.saveOneTimeMappingDetails(sourceEHRName, targetEHRName, serviceLine, mappingRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
