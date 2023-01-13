package com.org.census.migration.mapper;

import com.org.census.migration.dto.EHRMapping;
import com.org.census.migration.dto.MappingRequestDto;
import com.org.census.migration.dto.MappingResponseColumnsDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ModelEntityMapper {

    public abstract List<MappingResponseColumnsDto> toDTOs(List<EHRMapping> ehrMapping);

    public abstract MappingResponseColumnsDto toDTO(EHRMapping ehrMapping);

    @Mapping(target = "clientName", constant = "DEFAULT")
    public abstract EHRMapping toEntity(MappingRequestDto mappingRequestDto);

    public abstract List<EHRMapping> toEntities(List<MappingRequestDto> mappingRequestDto);
}