package com.org.census.migration.service;

import com.org.census.migration.constant.Constants;
import com.org.census.migration.converter.DtoToEntityConverter;
import com.org.census.migration.converter.EntityToDtoConverter;
import com.org.census.migration.exception.ResourceNotFoundException;
import com.org.census.migration.model.MappingMaster;
import com.org.census.migration.model.MappingMasterDto;
import com.org.census.migration.model.MappingMasterResponseDto;
import com.org.census.migration.repository.MappingMasterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class MappingMasterServiceImpl implements com.org.census.migration.service.MappingMasterService {

    @Autowired
    MappingMasterRepository mappingMasterRepository;

    @Override
    public void saveMappingMaster(String sourceEHRName, String targetEHRName, String serviceLine,
                                  String masterType, String clientName, List<MappingMasterDto> mappingMasterDto) {
        clientName = clientName == null ? Constants.DEFAULT_CLIENT_NAME : clientName;
        MappingMasterResponseDto mappingMasterResponseDto = MappingMasterResponseDto.builder().sourceEhrName(sourceEHRName)
                                                                                    .targetEhrName(targetEHRName)
                                                                                    .serviceLine(serviceLine)
                                                                                    .clientName(clientName)
                                                                                    .masterType(masterType)
                                                                                    .mappingMaster(mappingMasterDto).build();

        List<MappingMaster> mappingMasterRequestList = DtoToEntityConverter.toMappingMasterEntity(mappingMasterResponseDto);

        List<MappingMaster> mappingMasterExitingList = mappingMasterRepository.findBySourceEhrNameAndTargetEhrNameAndServiceLineAndClientNameAndMasterType(
                sourceEHRName, targetEHRName, serviceLine, clientName, masterType);
        List<MappingMaster> mappingMasterList = new ArrayList<>();
        for(MappingMaster mappingMaster: mappingMasterRequestList){
            if(mappingMasterExitingList.stream().noneMatch(M -> M.getSourceValue().equals(mappingMaster.getSourceValue()))){
                mappingMasterList.add(mappingMaster);
            }
        }
        mappingMasterRepository.saveAll(mappingMasterList);
    }

    @Override
    public MappingMasterResponseDto getMappingMaster(String sourceEHRName, String targetEHRName, String serviceLine,
                                                     String masterType, String clientName) {
        clientName = clientName == null ? Constants.DEFAULT_CLIENT_NAME : clientName;
        List<MappingMaster> mappingMasterList = mappingMasterRepository.findBySourceEhrNameAndTargetEhrNameAndServiceLineAndClientNameAndMasterType(
                sourceEHRName, targetEHRName, serviceLine, clientName, masterType);
        return  EntityToDtoConverter.toMappingMasterResponseDto(mappingMasterList);
    }

    @Override
    public void updateMappingMaster(String sourceEHRName, String targetEHRName, String serviceLine,
                                    String masterType, String clientName, List<MappingMasterDto> mappingMasterDto) {
        clientName = clientName == null ? Constants.DEFAULT_CLIENT_NAME : clientName;
        MappingMasterResponseDto mappingMasterResponseDto = MappingMasterResponseDto.builder().sourceEhrName(sourceEHRName)
                                                                                    .targetEhrName(targetEHRName)
                                                                                    .serviceLine(serviceLine)
                                                                                    .clientName(clientName)
                                                                                    .masterType(masterType)
                                                                                    .mappingMaster(mappingMasterDto).build();

        List<MappingMaster> mappingMasterRequestList = DtoToEntityConverter.toMappingMasterEntity(mappingMasterResponseDto);

        List<MappingMaster> mappingMasterExitingList = mappingMasterRepository.findBySourceEhrNameAndTargetEhrNameAndServiceLineAndClientNameAndMasterType(
                sourceEHRName, targetEHRName, serviceLine, clientName, masterType);
        List<MappingMaster> mappingMasterList = new ArrayList<>();
        for(MappingMaster mappingMaster: mappingMasterRequestList){
            if(mappingMasterExitingList.stream().noneMatch(M -> M.getSourceValue().equals(mappingMaster.getSourceValue()))){
                mappingMasterList.add(mappingMaster);
            } else {
                Optional<MappingMaster> updateMappingMaster = mappingMasterExitingList.stream().filter(M -> M.getSourceValue().equals(mappingMaster.getSourceValue())).findFirst();
                if(updateMappingMaster.isPresent()){
                    updateMappingMaster.get().setTargetValue(mappingMaster.getTargetValue());
                    mappingMasterList.add(updateMappingMaster.get());
                }
            }
        }
        mappingMasterRepository.saveAll(mappingMasterList);
    }

    @Override
    public void deleteMappingMaster(String sourceEHRName, String targetEHRName, String serviceLine,
                                    String masterType, String clientName, String sourceValue) {
        clientName = clientName == null ? Constants.DEFAULT_CLIENT_NAME : clientName;
        MappingMaster mappingMaster = mappingMasterRepository.findBySourceEhrNameAndTargetEhrNameAndServiceLineAndClientNameAndMasterTypeAndSourceValue(
                sourceEHRName, targetEHRName, serviceLine, clientName, masterType, sourceValue);
        if(Objects.nonNull(mappingMaster)){
            mappingMasterRepository.delete(mappingMaster);
        } else {
            throw new ResourceNotFoundException("Given value doesn't exist");
        }
    }
}
