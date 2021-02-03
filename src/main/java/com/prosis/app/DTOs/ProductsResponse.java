package com.prosis.app.DTOs;

import java.math.BigDecimal;

public interface ProductsResponse {
    int getId();

    String getImage();

    String getBarcode();

    String getName();

    String getDescription();

    int getInventoryMin();

    BigDecimal getPriceIn();

    BigDecimal getPriceOut1();

    BigDecimal getPriceOut2();

    BigDecimal getPriceOut3();

    String getUnit();

    String getPresentation();

    boolean getStatus();

    boolean getBulk();

    Double getInventoryOut3();

    Double getStock();

    String getCategory();
}
