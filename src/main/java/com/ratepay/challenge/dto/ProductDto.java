package com.ratepay.challenge.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProductDto {

    private String title;
    private String price;

    public ProductDto(String title, String price) {
        this.title = title;
        this.price = price;
    }
}