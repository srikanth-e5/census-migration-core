package com.org.census.migration.service;

import com.org.census.migration.constant.Constants;
import com.org.census.migration.mapper.ModelEntityMapper;
import com.org.census.migration.model.CreateMappingRequestDto;
import com.org.census.migration.model.EHRMapping;
import com.org.census.migration.model.MappingFieldsDto;
import com.org.census.migration.model.MappingResponseDto;
import com.org.census.migration.model.MappingResponseFieldsDto;
import com.org.census.migration.model.UpdateMappingRequestDto;
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
        Map<String,List<EHRMapping>> processEHRMap = responseList.stream().collect(Collectors.groupingBy(EHRMapping::getTargetProcessName));

        List<MappingResponseFieldsDto> ehrProcessMappingList =  new ArrayList<>();
        for (Map.Entry<String,List<EHRMapping>> entry : processEHRMap.entrySet()) {
            MappingResponseFieldsDto responseColumnsDto = new MappingResponseFieldsDto();
            List<MappingFieldsDto> responseColumnsList = modelEntityMapper.convertToEHRMappingResponseList(entry.getValue());
            responseColumnsDto.setFields(responseColumnsList);
            responseColumnsDto.setTargetProcessName(entry.getKey());
            ehrProcessMappingList.add(responseColumnsDto);
        }
        mappingResponseDto.setMapping(ehrProcessMappingList);
        mappingResponseDto.setServiceLine(serviceLine);
        mappingResponseDto.setSourceEHRType(sourceEHRName);
        mappingResponseDto.setTargetEHRType(targetEHRName);
        return mappingResponseDto;
    }

    @Override
    public void saveOneTimeMappingDetails(String sourceEHRName, String targetEHRName,
                                          String serviceLine, List<CreateMappingRequestDto> createMappingRequestDto) {
        List<EHRMapping> ehrMappingList = ehrMappingRepository.findBySourceEHRNameAndTargetEHRNameAndServiceLineAndClientName(
                sourceEHRName, targetEHRName, serviceLine, Constants.DEFAULT_CLIENT_NAME);
        if (!ehrMappingList.isEmpty()) {
            return;
        }
        List<EHRMapping> ehrOneTimeMapping = modelEntityMapper.convertToEHRMappingInfoList(createMappingRequestDto);
        ehrOneTimeMapping.forEach(ehrMapping -> {
            ehrMapping.setServiceLine(serviceLine);
            ehrMapping.setSourceEHRName(sourceEHRName);
            ehrMapping.setTargetEHRName(targetEHRName);
        });
        ehrMappingRepository.saveAll(ehrOneTimeMapping);
    }

    @Override
    public void updateOneTimeMappingDetails(String sourceEHRName, String targetEHRName, String serviceLine,
                                            List<UpdateMappingRequestDto> mappingRequestDto) {
        List<EHRMapping> updatedOneTimeMappingList = new ArrayList<>();
        List<EHRMapping> oneTimeEhrMappingList = ehrMappingRepository.findBySourceEHRNameAndTargetEHRNameAndServiceLineAndClientName(
                sourceEHRName, targetEHRName, serviceLine, Constants.DEFAULT_CLIENT_NAME);

        for (UpdateMappingRequestDto ehrMappingRequest : mappingRequestDto) {
            Optional<EHRMapping> ehrMapping = oneTimeEhrMappingList.stream()
                                                     .filter(s -> s.getTargetFieldName().equals(ehrMappingRequest.getTargetFieldName()) &&
                                                                  s.getTargetProcessName().equals(ehrMappingRequest.getTargetProcessName()))
                                                     .findFirst();
            if (ehrMapping.isPresent()) {
                ehrMapping.get().setSourceFileName(ehrMappingRequest.getSourceFileName());
                ehrMapping.get().setSourceSheetName(ehrMappingRequest.getSourceSheetName());
                ehrMapping.get().setSourceFieldName(ehrMappingRequest.getSourceFieldName());
                ehrMapping.get().setSourceFieldType(ehrMappingRequest.getSourceFieldType());
                ehrMapping.get().setSourceFieldFormat(ehrMappingRequest.getSourceFieldFormat());
                updatedOneTimeMappingList.add(ehrMapping.get());
            } else {
                EHRMapping ehrOneTimeMapping = modelEntityMapper.convertToEHRMappingInfo(ehrMappingRequest);
                ehrOneTimeMapping.setServiceLine(serviceLine);
                ehrOneTimeMapping.setSourceEHRName(sourceEHRName);
                ehrOneTimeMapping.setTargetEHRName(targetEHRName);
                updatedOneTimeMappingList.add(ehrOneTimeMapping);
            }
        }
        ehrMappingRepository.saveAll(updatedOneTimeMappingList);
    }

    @Override
    public void saveClientMappingDetails(String clientName, String sourceEHRName, String targetEHRName,
                                         String serviceLine) {
        int count = ehrMappingRepository.countBySourceEHRNameAndTargetEHRNameAndServiceLineAndClientName(sourceEHRName, targetEHRName, serviceLine, clientName);
        if (count > 0) {
            return;
        }
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

    @Override
    public void updateClientMappingDetails(String clientName, String sourceEHRName, String targetEHRName,
                                           String serviceLine, List<UpdateMappingRequestDto> updateMappingRequestDto) {

        List<EHRMapping> updatedClientMappingList = new ArrayList<>();
        List<EHRMapping> clientEhrMappingList = ehrMappingRepository.findBySourceEHRNameAndTargetEHRNameAndServiceLineAndClientName(
                sourceEHRName, targetEHRName, serviceLine, clientName);

        for (UpdateMappingRequestDto updateEHRMappingRequest : updateMappingRequestDto) {
            Optional<EHRMapping> ehrMapping = clientEhrMappingList.stream()
                                                                   .filter(s -> s.getTargetFieldName().equals(updateEHRMappingRequest.getTargetFieldName()) &&
                                                                                        s.getTargetProcessName().equals(updateEHRMappingRequest.getTargetProcessName()))
                                                                   .findFirst();
            if (ehrMapping.isPresent()) {
                ehrMapping.get().setSourceFileName(updateEHRMappingRequest.getSourceFileName());
                ehrMapping.get().setSourceSheetName(updateEHRMappingRequest.getSourceSheetName());
                ehrMapping.get().setSourceFieldName(updateEHRMappingRequest.getSourceFieldName());
                ehrMapping.get().setSourceFieldType(updateEHRMappingRequest.getSourceFieldType());
                ehrMapping.get().setSourceFieldFormat(updateEHRMappingRequest.getSourceFieldFormat());
                updatedClientMappingList.add(ehrMapping.get());
            } else {
                EHRMapping updatedEHRMapping = modelEntityMapper.convertToEHRMappingInfo(updateEHRMappingRequest);
                updatedEHRMapping.setServiceLine(serviceLine);
                updatedEHRMapping.setSourceEHRName(sourceEHRName);
                updatedEHRMapping.setTargetEHRName(targetEHRName);
                updatedClientMappingList.add(updatedEHRMapping);
            }
        }
        ehrMappingRepository.saveAll(updatedClientMappingList);

    }
}
