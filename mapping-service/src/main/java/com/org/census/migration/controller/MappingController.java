package com.org.census.migration.controller;

import com.org.census.migration.model.CreateMappingRequestDto;
import com.org.census.migration.model.MappingResponseDto;
import com.org.census.migration.model.UpdateMappingRequestDto;
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
                                                          List<CreateMappingRequestDto> createMappingRequestDto) {
        mappingService.saveOneTimeMappingDetails(sourceEHRName, targetEHRName, serviceLine, createMappingRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Override
    public ResponseEntity<Void> updateOneTimeMappingDetails(String sourceEHRName, String targetEHRName,
                                                            String serviceLine,
                                                            List<UpdateMappingRequestDto> mappingRequestDto) {
        mappingService.updateOneTimeMappingDetails(sourceEHRName, targetEHRName, serviceLine, mappingRequestDto);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @Override
    public ResponseEntity<Void> saveClientMappingDetails(String clientName, String sourceEHRName, String targetEHRName,
                                                         String serviceLine) {
        mappingService.saveClientMappingDetails(clientName, sourceEHRName, targetEHRName, serviceLine);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Override
    public ResponseEntity<Void> updateClientMappingDetails(String clientName, String sourceEHRName,
                                                           String targetEHRName, String serviceLine,
                                                           List<UpdateMappingRequestDto> mappingRequestDto) {

        mappingService.updateClientMappingDetails(clientName, sourceEHRName, targetEHRName, serviceLine, mappingRequestDto);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
