package com.org.census.migration.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MappingRequestDto {

    private String targetProcessName;

    private String targetFieldName;

    private Boolean isTargetFieldMandatory;

    private String sourceFileName;

    private String sourceSheetName;

    private String sourceFieldName;

    private String sourceFieldType;

    private String sourceFieldFormat;

}
