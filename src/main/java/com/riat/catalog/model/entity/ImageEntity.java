package com.riat.catalog.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.experimental.Accessors;

@Entity
@Table(name = "image")
@Data
@Accessors(chain = true)
public class ImageEntity {
    @Id
    @Column(name = "image_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long imageId;

    @Lob
    private byte[] content;
}
