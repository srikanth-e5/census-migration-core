package com.org.census.migration.controller;

import com.org.census.migration.model.MappingResponseDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.constraints.NotNull;

public interface MappingApi {

    @GetMapping("/mapping/sourceEHRName/{sourceEHRName}/targetEHRName/{targetEHRName}/serviceLine/{serviceLine}/details")
    MappingResponseDto getOneTimeMappingDetails(@PathVariable("sourceEHRName") @NotNull String sourceEHRName,
                                                @PathVariable("targetEHRName") @NotNull String targetEHRName,
                                                @PathVariable("serviceLine") @NotNull String serviceLine,
                                                @RequestParam(name="clientName", required = false) String clientName);
}
