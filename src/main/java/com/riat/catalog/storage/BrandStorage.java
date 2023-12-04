package com.riat.catalog.storage;

import com.riat.catalog.model.service.Brand;

import java.util.List;
import java.util.Optional;

public interface BrandStorage {
    List<Brand> findAll();

    Optional<Brand> findById(Long brandId);

    Brand save(Brand brand);

    Optional<Brand> findByName(String name);
}
