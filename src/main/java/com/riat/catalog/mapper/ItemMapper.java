package com.riat.catalog.mapper;

import com.riat.api.catalog.controllers.models.CatalogItemExtended;
import com.riat.api.catalog.controllers.models.CatalogItemIdPutRequest;
import com.riat.catalog.model.entity.ItemEntity;
import com.riat.catalog.model.service.Image;
import com.riat.catalog.model.service.Item;
import org.mapstruct.*;

import java.util.Optional;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = { ImageMapper.class, BrandMapper.class }, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public abstract class ItemMapper {
    public String imageToStringConverter(Image source) {
        return Optional.ofNullable(source)
                .map(Image::getImageId)
                .map(id -> "/images/" + id)
                .orElse(null);
    }

    public abstract ItemEntity mapToEntity(Item source);

    public abstract Item mapToBo(ItemEntity source);

    @Mapping(source = "itemId", target = "id")
    public abstract CatalogItemExtended mapToExtendedDto(Item source);

    @Mapping(source = "brand", target = "brand.name")
    public abstract Item mapToBo(CatalogItemIdPutRequest source);

    public abstract void updateBo(Item source, @MappingTarget Item target);
}
