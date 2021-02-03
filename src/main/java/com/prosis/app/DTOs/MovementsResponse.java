package com.prosis.app.DTOs;

import java.sql.Timestamp;

public interface MovementsResponse {
    int getId();

    String getProductName();

    String getOperation();

    int getQuantity();

    String getCashier();

    Timestamp getCreatedSell();

    String getCategory();
}
