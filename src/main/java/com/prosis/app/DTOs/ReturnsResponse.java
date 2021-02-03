package com.prosis.app.DTOs;

import java.math.BigDecimal;

public interface ReturnsResponse {
    int getFolio();

    int getProducts();

    BigDecimal getTotal();

    BigDecimal getCash();

    String getTime();

    String getCashier();

    String getClient();

    String getStatus();
}
