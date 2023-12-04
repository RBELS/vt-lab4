package com.riat.catalog.model.service;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Image {
    private Long imageId;
    private byte[] content;
}
