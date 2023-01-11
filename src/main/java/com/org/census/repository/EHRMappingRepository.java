package com.org.census.repository;

import com.org.census.entity.EHRMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface EHRMappingRepository extends JpaRepository<EHRMapping, UUID> {

    List<EHRMapping> findBySourceEHRNameAndTargetEHRNameAndServiceLineAndClientName(String sourceEHRName, String targetEHRName,
                                                                                    String serviceLine, String clientName);
}
