package com.org.census.controller;

import com.org.census.dto.MappingResponseDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

public interface MappingApi {

    @GetMapping("/mapping/sourceEHRType/{sourceEHRType}/targetEHRType/{targetEHRType}/serviceLine{serviceLine}details")
    MappingResponseDto getOneTimeMappingDetails(@PathVariable("sourceEHRType") String sourceEHRType,
                                                @PathVariable("targetEHRType") String targetEHRType,
                                                @PathVariable("serviceLine") String serviceLine);
}
