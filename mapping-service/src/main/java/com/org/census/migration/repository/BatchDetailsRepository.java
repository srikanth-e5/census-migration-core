package com.org.census.migration.repository;

import com.org.census.migration.model.BatchDetails;
import com.org.census.migration.model.MappingMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface BatchDetailsRepository extends JpaRepository<BatchDetails, UUID> {
    List<BatchDetails> findBySourceEhrNameAndTargetEhrNameAndServiceLineAndClientName(String sourceEHRType,
                                                                                       String targetEHRType,
                                                                                       String serviceLine,
                                                                                       String clientName);

    BatchDetails findFirstBySourceEhrNameAndTargetEhrNameAndServiceLineAndClientNameOrderByBatchNameDesc(String sourceEHRType,
                                                                                      String targetEHRType,
                                                                                      String serviceLine,
                                                                                      String clientName);

    BatchDetails findBySourceEhrNameAndTargetEhrNameAndServiceLineAndClientNameAndBatchName(String sourceEHRType,
                                                                                            String targetEHRType,
                                                                                            String serviceLine,
                                                                                            String clientName,
                                                                                            String batchName);
}
