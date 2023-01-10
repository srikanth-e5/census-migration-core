package com.org.census.controller;

import com.org.census.dto.MappingResponseDto;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClientMappingController implements ClientMappingApi {

    @Override
    public MappingResponseDto getMappingDetails(String clientName, String sourceEHRType, String targetEHRType,
                                                String serviceLine) {
        return null;
    }
}
