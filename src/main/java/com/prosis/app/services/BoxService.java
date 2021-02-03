package com.prosis.app.services;

import com.prosis.app.entities.BoxEntity;

import java.math.BigDecimal;

public interface BoxService {
    BoxEntity saveBox(String username, BigDecimal cashInBox);
}
