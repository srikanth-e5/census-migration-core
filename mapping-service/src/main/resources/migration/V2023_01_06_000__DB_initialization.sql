create extension if not exists "uuid-ossp";

CREATE TABLE ehr(
        id UUID NOT NULL PRIMARY KEY,
        ehr_name TEXT NOT NULL,
        process_name TEXT,
        sheet_name TEXT,
        field_name TEXT NOT NULL,
        field_type TEXT NOT NULL,
        created_on timestamptz DEFAULT CURRENT_TIMESTAMP,
        created_by TEXT,
        modified_on timestamptz DEFAULT CURRENT_TIMESTAMP,
        modified_by TEXT
);

CREATE INDEX idx_ehr_type ON ehr (ehr_name);

CREATE TABLE ehr_mapping(
        mapping_id UUID NOT NULL PRIMARY KEY,
        client_name TEXT NOT NULL,
        source_ehr_name TEXT NOT NULL,
        target_ehr_name TEXT NOT NULL,
        service_line TEXT NOT NULL,
        target_process_name TEXT NOT NULL,
        target_sheet_name TEXT NOT NULL,
        target_field_name TEXT NOT NULL,
        target_field_type TEXT NOT NULL,
        target_field_format TEXT,
        is_target_field_mandatory TEXT NOT NULL,
        source_file_name TEXT,
        source_sheet_name TEXT,
        source_field_name TEXT,
        source_field_type TEXT,
        source_field_format TEXT,
        created_on timestamptz DEFAULT CURRENT_TIMESTAMP,
        created_by TEXT,
        modified_on timestamptz DEFAULT CURRENT_TIMESTAMP,
        modified_by TEXT
);

CREATE INDEX idx_source_target_ehr_service_type ON ehr_mapping (source_ehr_name, target_ehr_name, service_line);

CREATE TABLE mapping_master(
        id UUID NOT NULL PRIMARY KEY,
        source_ehr_name TEXT NOT NULL,
        target_ehr_name TEXT NOT NULL,
        service_line TEXT NOT NULL,
        client_name TEXT,
        master_type TEXT NOT NULL,
        source_value TEXT NOT NULL,
        target_value TEXT NOT NULL,
        created_on timestamptz DEFAULT CURRENT_TIMESTAMP,
        created_by TEXT,
        modified_on timestamptz DEFAULT CURRENT_TIMESTAMP,
        modified_by TEXT
);

CREATE INDEX idx_client_source_target_ehr_service_line ON mapping_master (client_name, source_ehr_name, target_ehr_name, service_line, master_type);

CREATE TABLE batch_details(
        batch_id UUID NOT NULL PRIMARY KEY,
        source_ehr_name TEXT NOT NULL,
        target_ehr_name TEXT NOT NULL,
        service_line TEXT NOT NULL,
        client_name TEXT,
        batch_name TEXT,
        go_live_date DATE NOT NULL,
        created_on timestamptz DEFAULT CURRENT_TIMESTAMP,
        created_by TEXT,
        modified_on timestamptz DEFAULT CURRENT_TIMESTAMP,
        modified_by TEXT
);

CREATE TABLE processes(
        process_id UUID NOT NULL PRIMARY KEY,
        process_name TEXT NOT NULL,
        file_path TEXT,
        batch_id UUID
);

CREATE TABLE processes_upload_files(
        processes_process_id UUID NOT NULL PRIMARY KEY,
        upload_files TEXT NOT NULL
);