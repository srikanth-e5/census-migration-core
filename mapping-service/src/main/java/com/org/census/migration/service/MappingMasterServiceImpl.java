package com.org.census.migration.service;

import com.org.census.migration.converter.DtoToEntityConverter;
import com.org.census.migration.converter.EntityToDtoConverter;
import com.org.census.migration.dto.MappingMaster;
import com.org.census.migration.dto.MappingMasterDto;
import com.org.census.migration.dto.MappingMasterResponseDto;
import com.org.census.migration.repository.MappingMasterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

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
                                                      String clientName, String masterType,
                                                      List<MappingMasterDto> mappingMasterDto) {
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
                                                     String clientName, String masterType) {
        List<MappingMaster> mappingMasterList = mappingMasterRepository.findBySourceEhrNameAndTargetEhrNameAndServiceLineAndClientNameAndMasterType(
                sourceEHRName, targetEHRName, serviceLine, clientName, masterType);
        return  EntityToDtoConverter.toMappingMasterResponseDto(mappingMasterList);
    }

    @Override
    public void updateMappingMaster(String sourceEHRName, String targetEHRName, String serviceLine,
                                                        String clientName, String masterType,
                                                        List<MappingMasterDto> mappingMasterDto) {
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
                                                        String clientName, String masterType, String sourceValue) {
        MappingMaster mappingMaster = mappingMasterRepository.findBySourceEhrNameAndTargetEhrNameAndServiceLineAndClientNameAndMasterTypeAndSourceValue(
                sourceEHRName, targetEHRName, serviceLine, clientName, masterType, sourceValue);
        if(Objects.nonNull(mappingMaster)){
            mappingMasterRepository.delete(mappingMaster);
        } else {
            throw new NotFoundException("Given value doesn't exist");
        }
    }
}
