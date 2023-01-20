package com.org.census.migration.controller;

import com.org.census.migration.model.BatchDetailsDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import static com.org.census.migration.constant.Constants.ApiPath.BASE_API_PATH_V1;

@RequestMapping(BASE_API_PATH_V1)
public interface BatchDetailsApi {

    @PostMapping("/batch/details")
    @ResponseStatus(HttpStatus.CREATED)
    ResponseEntity<Void> saveBatchDetails(@RequestBody BatchDetailsDto batchDetailsDto);
}
