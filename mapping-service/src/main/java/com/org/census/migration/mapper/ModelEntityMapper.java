package com.org.census.migration.mapper;

import com.org.census.migration.model.MappingResponseColumnsDto;
import com.org.census.migration.entity.EHRMapping;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ModelEntityMapper {

    public abstract List<MappingResponseColumnsDto> toDTOs(List<EHRMapping> ehrMapping);

    public abstract MappingResponseColumnsDto toDTO(EHRMapping ehrMapping);


}