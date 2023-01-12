package com.org.census.migration.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.org.census.migration.constant.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import org.springframework.util.Assert;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class ErrorDTO {

    @NonNull
    private String code;
    @NonNull
    private String description;
    @NonNull
    private String detail;
    @NonNull
    @JsonProperty("trace_id")
    private String traceId;
    @Valid
    private final List<ErrorMessageDTO> errors;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Valid
    private final List<ErrorMessageDTO> warnings;

    public ErrorDTO(ErrorCode errorCode) {
        this(errorCode, "", "");
    }

    public ErrorDTO(ErrorCode errorCode, String detail, String traceId) {
        Assert.notNull(errorCode, "errorCode must not be null");
        this.code = errorCode.name();
        this.description = errorCode.getDescription();
        if (detail == null) {
            this.detail = "";
        } else {
            this.detail = detail;
        }
        this.traceId = traceId;
        errors = new ArrayList<>();
        warnings = new ArrayList<>();
    }

}
