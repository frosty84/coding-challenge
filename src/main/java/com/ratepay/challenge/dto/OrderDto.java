package com.ratepay.challenge.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class OrderDto {
    private List<ProductDto> products = new ArrayList<>();
    private String buyerId;

}