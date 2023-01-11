package com.org.census.mapper;

import com.org.census.model.MappingResponseColumnsDto;
import com.org.census.entity.EHRMapping;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ModelEntityMapper {

    public abstract List<MappingResponseColumnsDto> toDTOs(List<EHRMapping> ehrMapping);

    public abstract MappingResponseColumnsDto toDTO(EHRMapping ehrMapping);


}