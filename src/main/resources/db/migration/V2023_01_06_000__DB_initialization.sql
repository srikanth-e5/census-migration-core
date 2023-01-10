create extension if not exists "uuid-ossp";

CREATE TABLE ehr(
        ehr_id UUID NOT NULL PRIMARY KEY,
        ehr_type TEXT NOT NULL,
        process_type TEXT,
        column_name TEXT NOT NULL,
        column_type TEXT NOT NULL,
        creation_on timestamptz NOT NULL DEFAULT CURRENT_TIMESTAMP,
        created_by TEXT NOT NULL,
        modified_on timestamptz NOT NULL DEFAULT CURRENT_TIMESTAMP,
        modified_by TEXT
);

CREATE INDEX idx_ehr_type ON ehr (ehr_type);

CREATE TABLE ehr_mapping(
        mapping_id UUID NOT NULL PRIMARY KEY,
        client_name TEXT NOT NULL,
        source_ehr_type TEXT NOT NULL,
        target_ehr_type TEXT NOT NULL,
        service_line TEXT NOT NULL,
        target_process_type TEXT NOT NULL,
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
        creation_on timestamptz NOT NULL DEFAULT CURRENT_TIMESTAMP,
        created_by TEXT NOT NULL,
        modified_on timestamptz NOT NULL DEFAULT CURRENT_TIMESTAMP,
        modified_by TEXT
);

CREATE INDEX idx_source_target_ehr_service_type ON ehr_mapping (source_ehr_type, target_ehr_type, service_line);

CREATE TABLE mapping_master(
        id UUID NOT NULL PRIMARY KEY,
        source_ehr_type TEXT NOT NULL,
        target_ehr_type TEXT NOT NULL,
        service_line TEXT NOT NULL,
        client_name TEXT NOT NULL,
        master_type TEXT NOT NULL,
        source_value TEXT NOT NULL,
        target_value TEXT NOT NULL,
        creation_on timestamptz NOT NULL DEFAULT CURRENT_TIMESTAMP,
        created_by TEXT NOT NULL,
        modified_on timestamptz NOT NULL DEFAULT CURRENT_TIMESTAMP,
        modified_by TEXT
);

CREATE INDEX idx_client_source_target_ehr_service_line ON mapping_master (client_name, source_ehr_type, target_ehr_type, service_line, master_type);
