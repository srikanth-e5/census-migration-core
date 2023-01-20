package com.org.census.migration.controller;

import com.org.census.migration.model.MappingMasterDto;
import com.org.census.migration.model.MappingMasterResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

import static com.org.census.migration.constant.Constants.ApiPath.*;

@RequestMapping(BASE_API_PATH_V1)
public interface MappingMasterApi {
    @PostMapping(MAPPING + SOURCE_TARGET_EHR_SERVICE_LINE + "/masterType/{masterType}")
    @ResponseStatus(HttpStatus.CREATED)
    ResponseEntity<Void> saveMappingMaster(@PathVariable("sourceEHRName") String sourceEHRName,
                                           @PathVariable("targetEHRName") String targetEHRName,
                                           @PathVariable("serviceLine") String serviceLine,
                                           @PathVariable("masterType") String masterType,
                                           @RequestParam(name="clientName", required = false) String clientName,
                                           @RequestBody List<MappingMasterDto> mappingMasterDto);

    @GetMapping(MAPPING + SOURCE_TARGET_EHR_SERVICE_LINE + "/masterType/{masterType}")
    @ResponseStatus(HttpStatus.OK)
    MappingMasterResponseDto getMappingMaster(@PathVariable("sourceEHRName") String sourceEHRName,
                                              @PathVariable("targetEHRName") String targetEHRName,
                                              @PathVariable("serviceLine") String serviceLine,
                                              @PathVariable("masterType") String masterType,
                                              @RequestParam(name="clientName", required = false) String clientName);

    @PutMapping(MAPPING + SOURCE_TARGET_EHR_SERVICE_LINE + "/masterType/{masterType}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    ResponseEntity<Void> updateMappingMaster(@PathVariable("sourceEHRName") String sourceEHRName,
                                             @PathVariable("targetEHRName") String targetEHRName,
                                             @PathVariable("serviceLine") String serviceLine,
                                             @PathVariable("masterType") String masterType,
                                             @RequestParam(name="clientName", required = false) String clientName,
                                             @RequestBody List<MappingMasterDto> mappingMasterDto);

    @DeleteMapping(MAPPING + SOURCE_TARGET_EHR_SERVICE_LINE + "/masterType/{masterType}/sourceValue/{sourceValue}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    ResponseEntity<Void> deleteMappingMaster(@PathVariable("sourceEHRName") String sourceEHRName,
                                             @PathVariable("targetEHRName") String targetEHRName,
                                             @PathVariable("serviceLine") String serviceLine,
                                             @PathVariable("masterType") String masterType,
                                             @RequestParam(name="clientName", required = false) String clientName,
                                             @PathVariable("sourceValue") String sourceValue);
}
