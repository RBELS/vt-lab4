package com.riat.catalog.model.service;

import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.util.Set;

@Data
@Accessors(chain = true)
public class Item {
    private Long itemId;
    private String name;
    private String description;
    private BigDecimal price;
    private Brand brand;
    private Set<Image> images;
}
