package com.org.census.migration.service;

import com.org.census.migration.constant.Constants;
import com.org.census.migration.mapper.ModelEntityMapper;
import com.org.census.migration.model.EHRMapping;
import com.org.census.migration.model.EHRMaster;
import com.org.census.migration.model.MappingRequestDto;
import com.org.census.migration.model.MappingResponseColumnsDto;
import com.org.census.migration.model.MappingResponseDto;
import com.org.census.migration.repository.EHRMappingRepository;
import com.org.census.migration.repository.EHRRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Slf4j
public class MappingServiceImpl implements MappingService {

    @Autowired
    private EHRMappingRepository ehrMappingRepository;

    @Autowired
    private ModelEntityMapper modelEntityMapper;

    @Autowired
    private EHRRepository ehrRepository;

    @Override
    public MappingResponseDto getMappingDetails(String sourceEHRName, String targetEHRName, String serviceLine,
                                                String clientName) {
        clientName = clientName == null ? Constants.DEFAULT_CLIENT_NAME : clientName;
        List<EHRMapping> responseList = ehrMappingRepository.findBySourceEHRNameAndTargetEHRNameAndServiceLineAndClientName(
                sourceEHRName, targetEHRName, serviceLine, clientName);
        MappingResponseDto mappingResponseDto = new MappingResponseDto();
        List<MappingResponseColumnsDto> responseColumnsList = modelEntityMapper.toDTOs(responseList);
        mappingResponseDto.setMapping(responseColumnsList);
        mappingResponseDto.setServiceLine(serviceLine);
        mappingResponseDto.setSourceEHRType(sourceEHRName);
        mappingResponseDto.setTargetEHRType(targetEHRName);
        return mappingResponseDto;
    }

    @Override
    public void saveOneTimeMappingDetails(String sourceEHRName, String targetEHRName,
                                          String serviceLine, List<MappingRequestDto> mappingRequestDto) {
        List<EHRMapping> ehrMappingList = ehrMappingRepository.findBySourceEHRNameAndTargetEHRNameAndServiceLineAndClientName(
                sourceEHRName, targetEHRName, serviceLine, Constants.DEFAULT_CLIENT_NAME);
        if (!ehrMappingList.isEmpty()) {
            return;
        }
        List<EHRMapping> ehrOneTimeMapping = modelEntityMapper.toEntities(mappingRequestDto);
        List<EHRMaster> ehrMasterList = ehrRepository.findByEhrName(targetEHRName);
        Map<String, List<EHRMaster>> fieldMap = ehrMasterList.stream()
                                                             .collect(Collectors.groupingBy(EHRMaster::getFieldName));
        ehrOneTimeMapping.forEach(ehrMapping -> {
            ehrMapping.setServiceLine(serviceLine);
            ehrMapping.setSourceEHRName(sourceEHRName);
            ehrMapping.setTargetEHRName(targetEHRName);
            //TODO : This will break if same field name is available in multiple sheets or multiple times
            // check with jai and ask him to send values from front end.
            ehrMapping.setTargetFieldType(fieldMap.get(ehrMapping.getTargetFieldName()).get(0).getFieldType());
            ehrMapping.setTargetSheetName(fieldMap.get(ehrMapping.getTargetFieldName()).get(0).getSheetName());
        });
        ehrMappingRepository.saveAll(ehrOneTimeMapping);
    }

    @Override
    public void updateOneTimeMappingDetails(String sourceEHRName, String targetEHRName, String serviceLine,
                                            List<MappingRequestDto> mappingRequestDto) {
        List<EHRMapping> updatedOneTimeMappingList = new ArrayList<>();
        List<EHRMapping> oneTimeEhrMappingList = ehrMappingRepository.findBySourceEHRNameAndTargetEHRNameAndServiceLine(
                sourceEHRName, targetEHRName, serviceLine);

        List<EHRMaster> ehrMasterList = ehrRepository.findByEhrName(targetEHRName);
        Map<String, List<EHRMaster>> fieldMap = ehrMasterList.stream().collect(Collectors.groupingBy(EHRMaster::getFieldName));

        for (MappingRequestDto ehrMapping : mappingRequestDto) {
            List<EHRMapping> ehrMappingList = oneTimeEhrMappingList.stream()
                                                     .filter(s -> s.getTargetFieldName().equals(ehrMapping.getTargetFieldName()) &&
                                                                  s.getTargetProcessName().equals(ehrMapping.getTargetProcessName()))
                                                     .collect(Collectors.toList());
            if(ehrMappingList.isEmpty()) {
                EHRMapping ehrOneTimeMapping = modelEntityMapper.toEntity(ehrMapping);
                ehrOneTimeMapping.setServiceLine(serviceLine);
                ehrOneTimeMapping.setSourceEHRName(sourceEHRName);
                ehrOneTimeMapping.setTargetEHRName(targetEHRName);
                //TODO : This will break if same field name is available in multiple sheets or multiple times
                // check with jai and ask him to send values from front end.
                ehrOneTimeMapping.setTargetFieldType(fieldMap.get(ehrOneTimeMapping.getTargetFieldName()).get(0).getFieldType());
                ehrOneTimeMapping.setTargetSheetName(fieldMap.get(ehrOneTimeMapping.getTargetFieldName()).get(0).getSheetName());
                updatedOneTimeMappingList.add(ehrOneTimeMapping);
            } else {
                for (EHRMapping ehr : ehrMappingList) {
                    ehr.setSourceFileName(ehrMapping.getSourceFileName());
                    ehr.setSourceSheetName(ehrMapping.getSourceSheetName());
                    ehr.setSourceFieldName(ehrMapping.getSourceFieldName());
                    ehr.setSourceFieldType(ehrMapping.getSourceFieldType());
                    ehr.setSourceFieldFormat(ehrMapping.getSourceFieldFormat());
                    updatedOneTimeMappingList.add(ehr);
                }
            }
        }
        ehrMappingRepository.saveAll(updatedOneTimeMappingList);
    }

    @Override
    public void saveClientMappingDetails(String clientName, String sourceEHRName, String targetEHRName,
                                         String serviceLine) {
        List<EHRMapping> oneTimeMappingList = ehrMappingRepository.findBySourceEHRNameAndTargetEHRNameAndServiceLineAndClientName(
                sourceEHRName, targetEHRName, serviceLine, Constants.DEFAULT_CLIENT_NAME);
        List<EHRMapping> updatedOneTimeMappingList = new ArrayList<>();
        oneTimeMappingList.forEach(s-> {
            EHRMapping ehrMapping = new EHRMapping();
            BeanUtils.copyProperties(s, ehrMapping, "uuid", "clientName");
            ehrMapping.setClientName(clientName);
            updatedOneTimeMappingList.add(ehrMapping);
        });
        ehrMappingRepository.saveAll(updatedOneTimeMappingList);
    }
}
