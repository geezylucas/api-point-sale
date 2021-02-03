package com.prosis.app.DTOs;

import java.math.BigDecimal;

public interface DetailReturnResponse {
    int getId();

    String getProductName();

    int getQuantity();

    BigDecimal getPriceOut();

    BigDecimal getTotal();
}
