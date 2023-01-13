package com.org.census.migration.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MappingMasterDto implements Serializable {

    private String sourceValue;

    private String targetValue;

}
