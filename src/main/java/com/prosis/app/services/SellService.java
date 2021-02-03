package com.prosis.app.services;

import com.prosis.app.DTOs.*;

import java.util.List;

public interface SellService {
    SellResponse sellProducts(SellRequest sellRequest);

    List<MovementsResponse> getMovementsAllWithQuantity();

    List<ReturnsResponse> getReturnsToday();

    List<DetailReturnResponse> getDetailsReturn(int sellId);
}
