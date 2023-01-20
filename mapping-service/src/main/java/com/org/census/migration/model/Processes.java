package com.org.census.migration.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "processes")
public class Processes {

    @Id
    @GeneratedValue(
            strategy = GenerationType.AUTO
    )
    private UUID processId;

    @Column(name = "process_name")
    private String processName;

    @Column(name = "upload_files")
    @ElementCollection
    private List<String> uploadFiles;

    @Column(name = "file_path")
    private String filePath;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "batch_key", referencedColumnName = "batchId")
    @JsonIgnoreProperties("processesList")
    private BatchDetails batchDetails;
}
