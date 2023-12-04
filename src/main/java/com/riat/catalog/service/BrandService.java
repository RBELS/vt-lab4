package com.riat.catalog.service;

import com.riat.catalog.model.service.Brand;

import java.util.List;

public interface BrandService {
    List<Brand> findAll();

    Brand findById(Long brandId);

    Brand save(Brand brand);

    Brand findByName(String name);
}
