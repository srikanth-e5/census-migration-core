package com.org.census.migration.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProcessesDto {

    private String processName;

    private List<String> uploadFiles;

    private String filePath;
}
