package com.org.census.migration.converter;

import com.org.census.migration.model.MappingMaster;
import com.org.census.migration.model.MappingMasterDto;
import com.org.census.migration.model.MappingMasterResponseDto;

import java.util.ArrayList;
import java.util.List;

public class EntityToDtoConverter {
    public static MappingMasterResponseDto toMappingMasterResponseDto(List<MappingMaster> mappingMasterList) {
        if (mappingMasterList == null) {
            return null;
        } else {
            MappingMasterResponseDto mappingMasterResponseDto = new MappingMasterResponseDto();
            List<MappingMasterDto> mappingMasterDtoList = new ArrayList<>();
            for(MappingMaster mappingMaster : mappingMasterList) {
                MappingMasterDto mappingMasterDto = new MappingMasterDto();
                mappingMasterDto.setSourceValue(mappingMaster.getSourceValue());
                mappingMasterDto.setTargetValue(mappingMaster.getTargetValue());
                mappingMasterDtoList.add(mappingMasterDto);
                mappingMasterResponseDto.setSourceEhrName(mappingMaster.getSourceEhrName());
                mappingMasterResponseDto.setTargetEhrName(mappingMaster.getTargetEhrName());
                mappingMasterResponseDto.setServiceLine(mappingMaster.getServiceLine());
                mappingMasterResponseDto.setClientName(mappingMaster.getClientName());
                mappingMasterResponseDto.setMasterType(mappingMaster.getMasterType());
            }
            mappingMasterResponseDto.setMappingMaster(mappingMasterDtoList);
            return mappingMasterResponseDto;
        }
    }
}
