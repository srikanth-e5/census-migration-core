package com.org.census.migration.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BatchDetailsRequestDto {
    private String sourceEhrName;

    private String targetEhrName;

    private String serviceLine;

    private String clientName;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM/dd/YYYY")
    private Date goLiveDate;
}
