package com.org.census.migration.service;

import com.org.census.migration.model.EHRMapping;
import com.org.census.migration.model.EHRMaster;
import com.org.census.migration.mapper.ModelEntityMapper;
import com.org.census.migration.model.MappingRequestDto;
import com.org.census.migration.model.MappingResponseColumnsDto;
import com.org.census.migration.model.MappingResponseDto;
import com.org.census.migration.repository.EHRMappingRepository;
import com.org.census.migration.repository.EHRRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
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
        if (null != clientName && clientName.isBlank()) {
            clientName = "DEFAULT";
        }
        List<EHRMapping> responseList = ehrMappingRepository.findBySourceEHRNameAndTargetEHRNameAndServiceLineAndClientName(
                sourceEHRName, targetEHRName, serviceLine, clientName);
        MappingResponseDto mappingResponseDto = new MappingResponseDto();
        List<MappingResponseColumnsDto> responseColumnsDtos = modelEntityMapper.toDTOs(responseList);
        mappingResponseDto.setMapping(responseColumnsDtos);
        mappingResponseDto.setServiceLine(serviceLine);
        mappingResponseDto.setSourceEHRType(sourceEHRName);
        mappingResponseDto.setTargetEHRType(targetEHRName);
        return mappingResponseDto;
    }

    @Override
    public void saveOneTimeMappingDetails(String sourceEHRName, String targetEHRName,
                                                          String serviceLine, List<MappingRequestDto> mappingRequestDto) {
        List<EHRMapping> ehrOneTimeMapping = modelEntityMapper.toEntities(mappingRequestDto);

        List<EHRMaster> ehrMasterList = ehrRepository.findByEhrName(targetEHRName);
        Map<String, List<EHRMaster>> fieldMap = ehrMasterList.stream().collect(Collectors.groupingBy(EHRMaster::getFieldName));
        ehrOneTimeMapping.forEach( ehrMapping -> {
            ehrMapping.setServiceLine(serviceLine);
            ehrMapping.setSourceEHRName(sourceEHRName);
            ehrMapping.setTargetEHRName(targetEHRName);
            ehrMapping.setTargetFieldType(fieldMap.get(ehrMapping.getTargetFieldName()).get(0).getFieldType());
            ehrMapping.setTargetSheetName(fieldMap.get(ehrMapping.getTargetFieldName()).get(0).getSheetName());
        });
        ehrMappingRepository.saveAll(ehrOneTimeMapping);
    }
}
