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
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

public interface MappingMasterApi {
    @PostMapping("/mapping/sourceEHRName/{sourceEHRName}/targetEHRName/{targetEHRName}/serviceLine/{serviceLine}"
                        + "/clientName/{clientName}/masterType/{masterType}")
    @ResponseStatus(HttpStatus.CREATED)
    ResponseEntity<Void> saveMappingMaster(@PathVariable("sourceEHRName") String sourceEHRName,
                                           @PathVariable("targetEHRName") String targetEHRName,
                                           @PathVariable("serviceLine") String serviceLine,
                                           @PathVariable("clientName") String clientName,
                                           @PathVariable("masterType") String masterType,
                                           @RequestBody List<MappingMasterDto> mappingMasterDto);

    @GetMapping("/mapping/sourceEHRName/{sourceEHRName}/targetEHRName/{targetEHRName}/serviceLine/{serviceLine}"
                         + "/clientName/{clientName}/masterType/{masterType}")
    @ResponseStatus(HttpStatus.OK)
    MappingMasterResponseDto getMappingMaster(@PathVariable("sourceEHRName") String sourceEHRName,
                                               @PathVariable("targetEHRName") String targetEHRName,
                                               @PathVariable("serviceLine") String serviceLine,
                                               @PathVariable("clientName") String clientName,
                                               @PathVariable("masterType") String masterType);

    @PutMapping("/mapping/sourceEHRName/{sourceEHRName}/targetEHRName/{targetEHRName}/serviceLine/{serviceLine}"
                         + "/clientName/{clientName}/masterType/{masterType}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    ResponseEntity<Void> updateMappingMaster(@PathVariable("sourceEHRName") String sourceEHRName,
                                               @PathVariable("targetEHRName") String targetEHRName,
                                               @PathVariable("serviceLine") String serviceLine,
                                               @PathVariable("clientName") String clientName,
                                               @PathVariable("masterType") String masterType,
                                               @RequestBody List<MappingMasterDto> mappingMasterDto);

    @DeleteMapping("/mapping/sourceEHRName/{sourceEHRName}/targetEHRName/{targetEHRName}/serviceLine/{serviceLine}"
                        + "/clientName/{clientName}/masterType/{masterType}/sourceValue/{sourceValue}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    ResponseEntity<Void> deleteMappingMaster(@PathVariable("sourceEHRName") String sourceEHRName,
                                                 @PathVariable("targetEHRName") String targetEHRName,
                                                 @PathVariable("serviceLine") String serviceLine,
                                                 @PathVariable("clientName") String clientName,
                                                 @PathVariable("masterType") String masterType,
                                                 @PathVariable("sourceValue") String sourceValue);
}
