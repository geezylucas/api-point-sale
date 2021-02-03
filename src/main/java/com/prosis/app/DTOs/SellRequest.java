package com.prosis.app.DTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SellRequest {
    private int userId;
    private int boxId;
    private int personId;
    private BigDecimal total;
    private BigDecimal cash;
    private List<ProductsSellRequest> products;
}

