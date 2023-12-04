package com.riat.catalog.service.impl;

import com.riat.api.catalog.controllers.models.CatalogItemIdPutRequest;
import com.riat.catalog.mapper.ItemMapper;
import com.riat.catalog.model.service.Image;
import com.riat.catalog.model.service.Item;
import com.riat.catalog.service.BrandService;
import com.riat.catalog.service.ItemService;
import com.riat.catalog.storage.ImageStorage;
import com.riat.catalog.storage.ItemStorage;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {
    private final ItemStorage itemStorage;
    private final ImageStorage imageStorage;
    private final ItemMapper itemMapper;
    private final BrandService brandService;

    @Override
    @Transactional
    public List<Item> findAll() {
        return itemStorage.findAll();
    }

    @Override
    public Item findById(Long itemId) {
        return itemStorage.findById(itemId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @Override
    public void delete(Item item) {
        itemStorage.delete(item);
    }

    @Override
    public Item save(Item item) {
        item.setImages(new HashSet<>(imageStorage.saveAll(item.getImages())));
        return itemStorage.save(item);
    }

    @Override
    public Item update(Long itemId, CatalogItemIdPutRequest newItem) {
        Item updateItem = new Item()
                .setName(newItem.getName())
                .setDescription(newItem.getDescription())
                .setPrice(newItem.getPrice())
                .setBrand(Objects.nonNull(newItem.getBrand()) ? brandService.findByName(newItem.getBrand()) : null);
        Item container = findById(itemId);
        itemMapper.updateBo(updateItem, container);
        return itemStorage.save(container);
    }

    @Override
    public void addImages(Item item, List<Image> images) {
        item.getImages().addAll(imageStorage.saveAll(images));
        itemStorage.save(item);
    }
}
