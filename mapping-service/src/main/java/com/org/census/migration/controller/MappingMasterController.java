package com.org.census.migration.controller;

import com.org.census.migration.model.MappingMasterDto;
import com.org.census.migration.model.MappingMasterResponseDto;
import com.org.census.migration.service.MappingMasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MappingMasterController implements MappingMasterApi {

    @Autowired
    private MappingMasterService mappingMasterService;

    @Override
    public ResponseEntity<Void> saveMappingMaster(String sourceEHRName, String targetEHRName, String serviceLine,
                                                  String masterType, String clientName,
                                                  List<MappingMasterDto> mappingMasterDto) {
        if (null == clientName || clientName.isBlank()) {
            clientName = "DEFAULT";
        }
        mappingMasterService.saveMappingMaster(sourceEHRName, targetEHRName, serviceLine, clientName,
                                                      masterType, mappingMasterDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Override
    public MappingMasterResponseDto getMappingMaster(String sourceEHRName, String targetEHRName, String serviceLine,
                                                     String masterType, String clientName) {
        if (null == clientName || clientName.isBlank()) {
            clientName = "DEFAULT";
        }
        return mappingMasterService.getMappingMaster(sourceEHRName, targetEHRName, serviceLine, clientName, masterType);
    }

    @Override
    public ResponseEntity<Void> updateMappingMaster(String sourceEHRName, String targetEHRName, String serviceLine,
                                                    String masterType, String clientName,
                                                    List<MappingMasterDto> mappingMasterDto) {
        if (null == clientName || clientName.isBlank()) {
            clientName = "DEFAULT";
        }
        mappingMasterService.updateMappingMaster(sourceEHRName, targetEHRName, serviceLine, clientName, masterType, mappingMasterDto);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @Override
    public ResponseEntity<Void> deleteMappingMaster(String sourceEHRName, String targetEHRName, String serviceLine,
                                                    String masterType, String clientName, String sourceValue) {
        if (null == clientName || clientName.isBlank()) {
            clientName = "DEFAULT";
        }
        mappingMasterService.deleteMappingMaster(sourceEHRName, targetEHRName, serviceLine, clientName, masterType, sourceValue);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
