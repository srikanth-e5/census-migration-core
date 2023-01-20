package com.org.census.migration.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class MappingResponseFieldsDto {

    private String targetProcessName;

    private List<MappingFieldsDto> fields;

}
