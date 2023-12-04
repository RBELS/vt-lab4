package com.riat.catalog.controller;

import com.riat.api.catalog.controllers.ImageApi;
import com.riat.catalog.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ImageApiImpl implements ImageApi {
    private final ImageService imageService;

    @Override
    public ResponseEntity<Resource> imagesImageIdGet(Long imageId) {
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(new ByteArrayResource(
                        imageService.findById(imageId).getContent()
                ));
    }
}
