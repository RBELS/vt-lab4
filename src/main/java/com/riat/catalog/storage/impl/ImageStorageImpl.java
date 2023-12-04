package com.riat.catalog.storage.impl;

import com.riat.catalog.mapper.ImageMapper;
import com.riat.catalog.model.service.Image;
import com.riat.catalog.repository.ImageRepository;
import com.riat.catalog.storage.ImageStorage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ImageStorageImpl implements ImageStorage {
    private final ImageRepository imageRepository;
    private final ImageMapper imageMapper;

    @Override
    public List<Image> saveAll(Collection<Image> images) {
        return imageRepository.saveAll(images.stream().map(imageMapper::mapToEntity).toList()).stream()
                .map(imageMapper::mapToBo)
                .toList();
    }

    @Override
    public Optional<Image> findById(Long imageId) {
        return imageRepository.findById(imageId)
                .map(imageMapper::mapToBo);
    }
}
