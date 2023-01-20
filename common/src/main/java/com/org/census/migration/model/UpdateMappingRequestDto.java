package com.org.census.migration.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UpdateMappingRequestDto {

    private String targetProcessName;

    private String targetFieldName;
    // Added targetFieldType and targetSheetName. This will sent from UI form EHR Master.
    private String targetFieldType;

    private String targetSheetName;

    private Boolean isTargetFieldMandatory;

    private String sourceFileName;

    private String sourceSheetName;

    private String sourceFieldName;

    private String sourceFieldType;

    private String sourceFieldFormat;

}
