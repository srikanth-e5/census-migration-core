package com.org.census.migration.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "batch_details")
public class BatchDetails {
    @Id
    @GeneratedValue(
            strategy = GenerationType.AUTO
    )
    private UUID batchId;

    @Column(name = "source_ehr_name")
    private String sourceEhrName;

    @Column(name = "target_ehr_name")
    private String targetEhrName;

    @Column(name = "service_line")
    private String serviceLine;

    @Column(name = "client_name")
    private String clientName;

    @Column(name = "go_live_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM/dd/YYYY")
    private Date goLiveDate;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "batchDetails", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("batchDetails")
    private List<Processes> processesList = new ArrayList<>();

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
