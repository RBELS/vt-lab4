package com.riat.catalog.storage.impl;

import com.riat.catalog.mapper.ItemMapper;
import com.riat.catalog.model.service.Item;
import com.riat.catalog.repository.ItemRepository;
import com.riat.catalog.storage.ItemStorage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ItemStorageImpl implements ItemStorage {
    private final ItemRepository itemRepository;
    private final ItemMapper itemMapper;

    @Override
    public List<Item> findAll() {
        return itemRepository.findAll().stream()
                .map(itemMapper::mapToBo)
                .toList();
    }

    @Override
    @Transactional
    public Optional<Item> findById(Long itemId) {
        return itemRepository.findById(itemId)
                .map(itemMapper::mapToBo);
    }

    @Override
    public void delete(Item item) {
        itemRepository.delete(itemMapper.mapToEntity(item));
    }

    @Override
    public Item save(Item item) {
        return itemMapper.mapToBo(
                itemRepository.save(itemMapper.mapToEntity(item))
        );
    }
}
