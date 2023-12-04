package com.riat.catalog.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.experimental.Accessors;

@Entity
@Table(name = "brand")
@Data
@Accessors(chain = true)
public class BrandEntity {
    @Id
    @Column(name = "brand_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long brandId;

    @Column(name = "name")
    private String name;
}
