package com.org.census.migration.controller;

import com.org.census.migration.dto.MappingRequestDto;
import com.org.census.migration.dto.MappingResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

public interface MappingApi {

    @GetMapping("/mapping/sourceEHRName/{sourceEHRName}/targetEHRName/{targetEHRName}/serviceLine/{serviceLine}/details")
    MappingResponseDto getMappingDetails(@PathVariable("sourceEHRName") @NotNull String sourceEHRName,
                                         @PathVariable("targetEHRName") @NotNull String targetEHRName,
                                         @PathVariable("serviceLine") @NotNull String serviceLine,
                                         @RequestParam(name="clientName", required = false) String clientName);


    @PostMapping("/mapping/sourceEHRName/{sourceEHRName}/targetEHRName/{targetEHRName}/serviceLine/{serviceLine}/details")
    ResponseEntity<Void> saveOneTimeMappingDetails(@PathVariable("sourceEHRName") @NotNull String sourceEHRName,
                                                   @PathVariable("targetEHRName") @NotNull String targetEHRName,
                                                   @PathVariable("serviceLine") @NotNull String serviceLine,
                                                   @RequestBody @Valid List<MappingRequestDto> mappingRequestDto);
}
