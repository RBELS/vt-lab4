package com.riat.catalog.service;

import com.riat.catalog.model.service.Image;

import java.util.Collection;
import java.util.List;

public interface ImageService {
    List<Image> saveAll(Collection<Image> images);

    Image findById(Long imageId);
}
