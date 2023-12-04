package com.riat.catalog.service.impl;

import com.riat.catalog.model.service.Brand;
import com.riat.catalog.service.BrandService;
import com.riat.catalog.storage.BrandStorage;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BrandServiceImpl implements BrandService {
    private final BrandStorage brandStorage;

    @Override
    public List<Brand> findAll() {
        return brandStorage.findAll();
    }

    @Override
    public Brand findById(Long brandId) {
        return brandStorage.findById(brandId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @Override
    public Brand save(Brand brand) {
        return brandStorage.save(brand);
    }

    @Override
    public Brand findByName(String name) {
        return brandStorage.findByName(name)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
}
