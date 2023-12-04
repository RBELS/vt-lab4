package com.riat.catalog.storage;

import com.riat.catalog.model.service.Item;

import java.util.List;
import java.util.Optional;

public interface ItemStorage {
    List<Item> findAll();

    Optional<Item> findById(Long itemId);

    void delete(Item item);

    Item save(Item item);
}
