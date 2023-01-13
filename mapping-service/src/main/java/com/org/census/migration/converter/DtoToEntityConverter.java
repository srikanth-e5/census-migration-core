package com.org.census.migration.converter;

import com.org.census.migration.dto.MappingMaster;
import com.org.census.migration.dto.MappingMasterDto;
import com.org.census.migration.dto.MappingMasterResponseDto;

import java.util.ArrayList;
import java.util.List;

public class DtoToEntityConverter {

    public static List<MappingMaster> toMappingMasterEntity(MappingMasterResponseDto mappingMasterResponseDto) {
        if (mappingMasterResponseDto == null) {
            return null;
        } else {
            List<MappingMaster> mappingMasterList = new ArrayList<MappingMaster>();
            for(MappingMasterDto mappingMasterDto : mappingMasterResponseDto.getMappingMaster()){
                MappingMaster mappingMaster = new MappingMaster();
                mappingMaster.setSourceValue(mappingMasterDto.getSourceValue());
                mappingMaster.setTargetValue(mappingMasterDto.getTargetValue());
                mappingMaster.setSourceEhrName(mappingMasterResponseDto.getSourceEhrName());
                mappingMaster.setTargetEhrName(mappingMasterResponseDto.getTargetEhrName());
                mappingMaster.setServiceLine(mappingMasterResponseDto.getServiceLine());
                mappingMaster.setClientName(mappingMasterResponseDto.getClientName());
                mappingMaster.setMasterType(mappingMasterResponseDto.getMasterType());
                mappingMasterList.add(mappingMaster);
            }
            return mappingMasterList;
        }
    }
}
