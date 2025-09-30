package com.ratepay.challenge.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Setter
@Getter
public class OrderDto {
    private UUID id;
    private List<ProductDto> products = new ArrayList<>();
    private String buyerId;

}