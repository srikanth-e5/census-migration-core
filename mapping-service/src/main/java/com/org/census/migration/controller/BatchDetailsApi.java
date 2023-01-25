package com.org.census.migration.controller;

import com.org.census.migration.model.BatchDetailsDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

import static com.org.census.migration.constant.Constants.ApiPath.*;

@RequestMapping(BASE_API_PATH_V1)
public interface BatchDetailsApi {

    @PostMapping(value = "/batch/details", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE,
            MediaType.APPLICATION_JSON_VALUE })
    @ResponseStatus(HttpStatus.CREATED)
    ResponseEntity<Void> saveBatchDetails(@RequestPart("batchDetailsDto") String batchDetailsDto,
                                          @RequestPart(name = "patientCreationFiles", required = false) List<MultipartFile> patientCreationFiles);

    @GetMapping(SOURCE_TARGET_EHR_SERVICE_LINE + CLIENT_NAME + "/batch/{batchName}/details")
    @ResponseStatus(HttpStatus.OK)
    BatchDetailsDto getBatchDetails(@PathVariable("sourceEHRName") String sourceEHRName,
                                    @PathVariable("targetEHRName") String targetEHRName,
                                    @PathVariable("serviceLine") String serviceLine,
                                    @PathVariable("clientName") String clientName,
                                    @PathVariable("batchName") String batchName);

    @GetMapping(SOURCE_TARGET_EHR_SERVICE_LINE + CLIENT_NAME + "/details")
    @ResponseStatus(HttpStatus.OK)
    List<BatchDetailsDto> getBatchDetailsList(@PathVariable("sourceEHRName") String sourceEHRName,
                                          @PathVariable("targetEHRName") String targetEHRName,
                                          @PathVariable("serviceLine") String serviceLine,
                                          @PathVariable("clientName") String clientName);

    @GetMapping(SOURCE_TARGET_EHR_SERVICE_LINE + CLIENT_NAME + "/targetProcessName/{targetProcessName}")
    @ResponseStatus(HttpStatus.OK)
    boolean uploadFileValidation(@PathVariable("sourceEHRName") String sourceEHRName,
                                                 @PathVariable("targetEHRName") String targetEHRName,
                                                 @PathVariable("serviceLine") String serviceLine,
                                                 @PathVariable("clientName") String clientName,
                                                 @PathVariable("targetProcessName") String targetProcessName,
                                                 @RequestParam(name="file", required = false) MultipartFile file) throws IOException;

}
