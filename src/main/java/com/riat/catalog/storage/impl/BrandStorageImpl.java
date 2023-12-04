package com.riat.catalog.storage.impl;

import com.riat.catalog.mapper.BrandMapper;
import com.riat.catalog.model.service.Brand;
import com.riat.catalog.repository.BrandRepository;
import com.riat.catalog.storage.BrandStorage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class BrandStorageImpl implements BrandStorage {
    private final BrandRepository brandRepository;
    private final BrandMapper brandMapper;

    @Override
    public List<Brand> findAll() {
        return brandRepository.findAll().stream()
                .map(brandMapper::mapToBo)
                .toList();
    }

    @Override
    public Optional<Brand> findById(Long brandId) {
        return brandRepository.findById(brandId)
                .map(brandMapper::mapToBo);
    }

    @Override
    public Brand save(Brand brand) {
        return brandMapper.mapToBo(
                brandRepository.save(brandMapper.mapToEntity(brand))
        );
    }

    @Override
    public Optional<Brand> findByName(String name) {
        return brandRepository.findByName(name)
                .map(brandMapper::mapToBo);
    }
}
