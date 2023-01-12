package com.org.census.migration.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ehr_mapping")
public class EHRMapping {

    @Id
    @GeneratedValue
    @Column(name = "mapping_id", columnDefinition = "uuid")
    private UUID uuid;

    @NotNull
    @Column(name = "client_name")
    private String clientName;

    @NotNull
    @Column(name = "source_ehr_name")
    private String sourceEHRName;

    @NotNull
    @Column(name = "target_ehr_name")
    private String targetEHRName;

    @NotNull
    @Column(name = "service_line")
    private String serviceLine;

    @NotNull
    @Column(name = "target_process_name")
    private String targetProcessName;

    @NotNull
    @Column(name = "target_sheet_name")
    private String targetSheetName;

    @NotNull
    @Column(name = "target_field_name")
    private String targetFieldName;

    @NotNull
    @Column(name = "target_field_type")
    private String targetFieldType;

    @Column(name = "target_field_format")
    private String targetFieldFormat;

    @NotNull
    @Column(name = "is_target_field_mandatory")
    private Boolean isTargetFieldMandatory;

    @Column(name = "source_file_name")
    private String sourceFileName;

    @Column(name = "source_sheet_name")
    private String sourceSheetName;

    @Column(name = "source_field_name")
    private String sourceFieldName;

    @Column(name = "source_field_type")
    private String sourceFieldType;

    @Column(name = "source_field_format")
    private String sourceFieldFormat;

    @CreatedDate
    @Column(name = "created_on")
    private String createdOn;

    @CreatedBy
    @Column(name = "created_by")
    private String createdBy;

    @LastModifiedDate
    @Column(name = "modified_on")
    private String modifiedOn;

    @LastModifiedBy
    @Column(name = "modified_by")
    private String modifiedBy;
}
