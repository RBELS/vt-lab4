package com.riat.catalog.mapper;

import com.riat.api.catalog.controllers.models.BrandItem;
import com.riat.api.catalog.controllers.models.BrandsPostRequest;
import com.riat.catalog.model.entity.BrandEntity;
import com.riat.catalog.model.service.Brand;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface BrandMapper {
    BrandEntity mapToEntity(Brand source);

    Brand mapToBo(BrandEntity source);

    @Mapping(source = "brandId", target = "id")
    BrandItem mapToDto(Brand source);

    Brand mapToBo(BrandsPostRequest source);
}
