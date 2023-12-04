package com.riat.catalog.service.impl;

import com.riat.catalog.model.service.Image;
import com.riat.catalog.service.ImageService;
import com.riat.catalog.storage.ImageStorage;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {
    private final ImageStorage imageStorage;

    @Override
    public List<Image> saveAll(Collection<Image> images) {
        return imageStorage.saveAll(images);
    }

    @Override
    public Image findById(Long imageId) {
        return imageStorage.findById(imageId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
}
