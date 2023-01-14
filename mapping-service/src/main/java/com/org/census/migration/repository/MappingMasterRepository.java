package com.org.census.migration.repository;

import com.org.census.migration.model.MappingMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface MappingMasterRepository extends JpaRepository<MappingMaster, UUID> {

    List<MappingMaster> findBySourceEhrNameAndTargetEhrNameAndServiceLineAndClientNameAndMasterType(String sourceEHRType,
                                                                                                 String targetEHRType,
                                                                                                 String serviceLine,
                                                                                                 String clientName,
                                                                                                 String masterType);

    MappingMaster findBySourceEhrNameAndTargetEhrNameAndServiceLineAndClientNameAndMasterTypeAndSourceValue(String sourceEHRType,
                                                                                                    String targetEHRType,
                                                                                                    String serviceLine,
                                                                                                    String clientName,
                                                                                                    String masterType,
                                                                                                    String sourceValue);
}
