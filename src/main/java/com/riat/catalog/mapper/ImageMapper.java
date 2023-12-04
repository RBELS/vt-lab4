package com.riat.catalog.mapper;

import com.riat.catalog.model.entity.ImageEntity;
import com.riat.catalog.model.service.Image;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ImageMapper {
    ImageEntity mapToEntity(Image source);

    Image mapToBo(ImageEntity source);
}
