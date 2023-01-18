package com.org.census.migration.controller;

import com.org.census.migration.model.MappingRequestDto;
import com.org.census.migration.model.MappingResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

import static com.org.census.migration.constant.Constants.ApiPath.*;

@RequestMapping(BASE_API_PATH_V1)
public interface MappingApi {

    @GetMapping(MAPPING + SOURCE_TARGET_EHR_SERVICE_LINE + "/details")
    MappingResponseDto getMappingDetails(@PathVariable("sourceEHRName") @NotNull String sourceEHRName,
                                         @PathVariable("targetEHRName") @NotNull String targetEHRName,
                                         @PathVariable("serviceLine") @NotNull String serviceLine,
                                         @RequestParam(name = "clientName", required = false) String clientName);

    @PostMapping(MAPPING + SOURCE_TARGET_EHR_SERVICE_LINE + "/details")
    ResponseEntity<Void> saveOneTimeMappingDetails(@PathVariable("sourceEHRName") @NotNull String sourceEHRName,
                                                   @PathVariable("targetEHRName") @NotNull String targetEHRName,
                                                   @PathVariable("serviceLine") @NotNull String serviceLine,
                                                   @RequestBody @Valid List<MappingRequestDto> mappingRequestDto);

    @PutMapping(MAPPING + SOURCE_TARGET_EHR_SERVICE_LINE + "/details")
    ResponseEntity<Void> updateOneTimeMappingDetails(@PathVariable("sourceEHRName") @NotNull String sourceEHRName,
                                                   @PathVariable("targetEHRName") @NotNull String targetEHRName,
                                                   @PathVariable("serviceLine") @NotNull String serviceLine,
                                                   @RequestBody @Valid List<MappingRequestDto> mappingRequestDto);

    @PostMapping(MAPPING + CLIENT_NAME + SOURCE_TARGET_EHR_SERVICE_LINE + "/details")
    ResponseEntity<Void> saveClientMappingDetails(@PathVariable("clientName") @NotNull String clientName,
                                                  @PathVariable("sourceEHRName") @NotNull String sourceEHRName,
                                                  @PathVariable("targetEHRName") @NotNull String targetEHRName,
                                                  @PathVariable("serviceLine") @NotNull String serviceLine);
}
