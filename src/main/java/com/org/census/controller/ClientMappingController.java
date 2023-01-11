package com.org.census.controller;

import com.org.census.model.MappingResponseDto;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClientMappingController implements ClientMappingApi {

    @Override
    public MappingResponseDto getMappingDetails(String clientName, String sourceEHRType, String targetEHRType,
                                                String serviceLine) {
        return null;
    }
}
