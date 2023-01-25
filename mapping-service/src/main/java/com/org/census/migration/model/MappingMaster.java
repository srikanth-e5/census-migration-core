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
import java.time.Instant;
import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "mapping_master")
public class MappingMaster {
    @Id
    @GeneratedValue(
            strategy = GenerationType.AUTO
    )
    private UUID id;

    @Column(name = "source_ehr_name")
    private String sourceEhrName;

    @Column(name = "target_ehr_name")
    private String targetEhrName;

    @Column(name = "service_line")
    private String serviceLine;

    @Column(name = "client_name")
    private String clientName;

    @Column(name = "master_type")
    private String masterType;

    @Column(name = "source_value")
    private String sourceValue;

    @Column(name = "target_value")
    private String targetValue;

    @Column(name = "created_on")
    @CreatedDate
    private Instant createdOn;

    @CreatedBy
    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "modified_on")
    @LastModifiedDate
    private Instant modifiedOn;

    @Column(name = "modified_by")
    @LastModifiedBy
    private String modifiedBy;
}
