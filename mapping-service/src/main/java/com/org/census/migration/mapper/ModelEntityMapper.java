package com.org.census.migration.mapper;

import com.org.census.migration.model.EHRMapping;
import com.org.census.migration.model.MappingRequestDto;
import com.org.census.migration.model.MappingResponseColumnsDto;
import com.org.census.migration.model.UpdateMappingRequestDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ModelEntityMapper {

    public abstract List<MappingResponseColumnsDto> convertToEHRMappingResponseList(List<EHRMapping> ehrMapping);

    public abstract MappingResponseColumnsDto convertToEHRMappingResponse(EHRMapping ehrMapping);

    @Mapping(target = "clientName", constant = "DEFAULT")
    public abstract EHRMapping convertToEHRMappingInfo(MappingRequestDto mappingRequestDto);

    @Mapping(target = "clientName", constant = "DEFAULT")
    public abstract EHRMapping convertToEHRMappingInfo(UpdateMappingRequestDto mappingRequestDto);

    public abstract List<EHRMapping> convertToEHRMappingInfoList(List<MappingRequestDto> mappingRequestDto);
}