package com.org.census.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MappingResponseDto {

    private String sourceEHRType;

    private String destinationEHRType;

    private String serviceLine;

    private List<MappingResponseColumnsDto> mapping;
}
