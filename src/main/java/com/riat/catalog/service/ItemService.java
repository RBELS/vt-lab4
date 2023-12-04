package com.riat.catalog.service;

import com.riat.api.catalog.controllers.models.CatalogItemIdPutRequest;
import com.riat.catalog.model.service.Image;
import com.riat.catalog.model.service.Item;

import java.util.List;

public interface ItemService {
    List<Item> findAll();

    Item findById(Long itemId);

    void delete(Item item);

    Item save(Item item);

    Item update(Long itemId, CatalogItemIdPutRequest newItem);

    void addImages(Item item, List<Image> images);
}
