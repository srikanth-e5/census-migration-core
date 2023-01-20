package com.org.census.migration.mapper;

import com.org.census.migration.model.BatchDetails;
import com.org.census.migration.model.BatchDetailsDto;
import com.org.census.migration.model.EHRMapping;
import com.org.census.migration.model.MappingFieldsDto;
import com.org.census.migration.model.CreateMappingRequestDto;
import com.org.census.migration.model.UpdateMappingRequestDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ModelEntityMapper {

    public abstract List<MappingFieldsDto> convertToEHRMappingResponseList(List<EHRMapping> ehrMapping);

    public abstract MappingFieldsDto convertToEHRMappingResponse(EHRMapping ehrMapping);

    @Mapping(target = "clientName", constant = "DEFAULT")
    public abstract EHRMapping convertToEHRMappingInfo(CreateMappingRequestDto createMappingRequestDto);

    @Mapping(target = "clientName", constant = "DEFAULT")
    public abstract EHRMapping convertToEHRMappingInfo(UpdateMappingRequestDto mappingRequestDto);

    public abstract List<EHRMapping> convertToEHRMappingInfoList(List<CreateMappingRequestDto> createMappingRequestDto);

    public abstract BatchDetailsDto toBatchDetailsDTO(BatchDetails batchDetails);

    public abstract BatchDetails toBatchDetailsEntity(BatchDetailsDto batchDetailsDto);
}