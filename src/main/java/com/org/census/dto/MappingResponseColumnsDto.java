package com.org.census.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MappingResponseColumnsDto {

    private String targetProcessType;

    private String targetSheetName;

    private String targetFieldName;

    private String targetFieldType;

    private String targetFieldFormat;

    private String isMandatoryTargetField;

    private String sourceFileName;

    private String sourceSheetName;

    private String sourceFieldName;

    private String sourceFieldType;

    private String sourceFieldFormat;

    private String mapping;

}
