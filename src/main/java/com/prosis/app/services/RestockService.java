package com.prosis.app.services;

import com.prosis.app.DTOs.ProductsResponse;
import com.prosis.app.DTOs.RestockRequest;
import com.prosis.app.DTOs.RestockResponse;

public interface RestockService {
    RestockResponse restockByProductId(RestockRequest restockRequest);

    ProductsResponse findProductByBarcodeWithQuantity(String barcode);
}
