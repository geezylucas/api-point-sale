package com.prosis.app.services;

import com.prosis.app.DTOs.*;

public interface RestockService {
    RestockResponse restockByProductId(RestockRequest restockRequest);

    ProductsResponse findProductByBarcodeWithQuantity(String barcode);

    SellResponse removeStockByProductId(ProductsSellRequest productsSellRequest);
}
