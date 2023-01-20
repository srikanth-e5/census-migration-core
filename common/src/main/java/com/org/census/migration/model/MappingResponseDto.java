package com.org.census.migration.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class MappingResponseDto {

    private String sourceEHRType;

    private String targetEHRType;

    private String serviceLine;

    private List<MappingResponseFieldsDto> mapping;
}
