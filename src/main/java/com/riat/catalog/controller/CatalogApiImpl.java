package com.riat.catalog.controller;

import com.riat.api.catalog.controllers.CatalogApi;
import com.riat.api.catalog.controllers.models.CatalogItemExtended;
import com.riat.api.catalog.controllers.models.CatalogItemIdPutRequest;
import com.riat.catalog.mapper.ItemMapper;
import com.riat.catalog.model.service.Image;
import com.riat.catalog.model.service.Item;
import com.riat.catalog.service.BrandService;
import com.riat.catalog.service.ItemService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class CatalogApiImpl implements CatalogApi {
    private final ItemMapper itemMapper;
    private final ItemService itemService;
    private final BrandService brandService;

    @Override
    public ResponseEntity<List<CatalogItemExtended>> catalogGet() {
        return ResponseEntity.ok(itemService.findAll().stream()
                .map(itemMapper::mapToExtendedDto)
                .toList()
        );
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CatalogItemExtended> catalogItemIdDelete(Long itemId) {
        Item item = itemService.findById(itemId);
        itemService.delete(item);
        return ResponseEntity.ok(itemMapper.mapToExtendedDto(item));
    }

    @Override
    public ResponseEntity<CatalogItemExtended> catalogItemIdGet(Long itemId) {
        return ResponseEntity.ok(itemMapper.mapToExtendedDto(itemService.findById(itemId)));
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> catalogItemIdImagesPut(Long itemId, List<MultipartFile> images) {
        List<Image> imageList = Objects.isNull(images) ? new ArrayList<>() : images.stream().map(file -> {
            try {
                return new Image()
                        .setContent(file.getBytes());
            } catch (IOException e) {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }).toList();
        itemService.addImages(
                itemService.findById(itemId), imageList
        );
        return ResponseEntity.ok().build();
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CatalogItemExtended> catalogItemIdPut(Long itemId, @Valid CatalogItemIdPutRequest catalogItemIdPutRequest) {
        return ResponseEntity.ok(itemMapper.mapToExtendedDto(
                itemService.update(itemId, catalogItemIdPutRequest)
        ));
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CatalogItemExtended> catalogPost(@Valid String name, @Valid String brand, @Valid String description, @Valid Double price, List<MultipartFile> images) {
        return ResponseEntity.ok(
                itemMapper.mapToExtendedDto(itemService.save(new Item()
                        .setName(name)
                        .setDescription(description)
                        .setPrice(BigDecimal.valueOf(price))
                        .setBrand(brandService.findByName(brand))
                        .setImages(images.stream().map(multipartFile -> {
                            try {
                                return new Image().setContent(multipartFile.getBytes());
                            } catch (IOException e) {
                                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
                            }
                        }).collect(Collectors.toSet()))
                ))
        );
    }
}
