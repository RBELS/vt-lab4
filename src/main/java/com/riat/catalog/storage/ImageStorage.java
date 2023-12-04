package com.riat.catalog.storage;

import com.riat.catalog.model.service.Image;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface ImageStorage {
    List<Image> saveAll(Collection<Image> images);

    Optional<Image> findById(Long imageId);
}
