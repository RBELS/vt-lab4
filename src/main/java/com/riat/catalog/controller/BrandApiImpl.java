package com.riat.catalog.controller;

import com.riat.api.catalog.controllers.BrandApi;
import com.riat.api.catalog.controllers.models.BrandItem;
import com.riat.api.catalog.controllers.models.BrandsPostRequest;
import com.riat.catalog.mapper.BrandMapper;
import com.riat.catalog.service.BrandService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BrandApiImpl implements BrandApi {
    private final BrandService brandService;
    private final BrandMapper brandMapper;

    @Override
    public ResponseEntity<List<BrandItem>> brandsGet() {
        return ResponseEntity.ok(brandService.findAll().stream().map(brandMapper::mapToDto).toList());
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<BrandItem> brandsPost(@Valid BrandsPostRequest brandsPostRequest) {
        return ResponseEntity.ok(brandMapper.mapToDto(
                brandService.save(brandMapper.mapToBo(brandsPostRequest))
        ));
    }
}
