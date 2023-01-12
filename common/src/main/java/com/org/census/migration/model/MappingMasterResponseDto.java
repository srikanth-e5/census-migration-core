package com.org.census.migration.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MappingMasterResponseDto implements Serializable {

    private String sourceEhrName;

    private String targetEhrName;

    private String serviceLine;

    private String clientName;

    private String masterType;

    private List<MappingMasterDto> mappingMaster;
}
