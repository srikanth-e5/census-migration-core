package com.org.census.migration.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MappingResponseColumnsDto {

    private String targetProcessName;

    private String targetSheetName;

    private String targetFieldName;

    private String targetFieldType;

    private String targetFieldFormat;

    private Boolean isTargetFieldMandatory;

    private String sourceFileName;

    private String sourceSheetName;

    private String sourceFieldName;

    private String sourceFieldType;

    private String sourceFieldFormat;

}
