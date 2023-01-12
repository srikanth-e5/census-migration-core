package com.org.census.migration.model;

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
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ehr")
public class EHRMaster {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "ehr_name")
    @NotNull
    private String ehrName;

    @Column(name = "process_name")
    private String processName;

    @Column(name = "sheet_name")
    private String sheetName;

    @NotNull
    @Column(name = "field_name")
    private String fieldName;

    @NotNull
    @Column(name = "field_type")
    private String fieldType;

    @Column(name = "created_on")
    @CreatedDate
    private OffsetDateTime createdOn;

    @CreatedBy
    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "modified_on")
    @LastModifiedDate
    private OffsetDateTime modifiedOn;

    @Column(name = "modified_by")
    @LastModifiedBy
    private String modifiedBy;
}
