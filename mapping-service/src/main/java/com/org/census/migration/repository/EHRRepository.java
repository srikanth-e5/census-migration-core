package com.org.census.migration.repository;

import com.org.census.migration.dto.EHRMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface EHRRepository extends JpaRepository<EHRMaster, UUID> {

    List<EHRMaster> findByEhrName(String ehrName);
}
