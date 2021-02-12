package com.prosis.app.controllers;

import com.prosis.app.DTOs.*;
import com.prosis.app.services.RestockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/restock")
public class RestockController {
    private final RestockService restockService;

    @Autowired
    public RestockController(RestockService restockService) {
        this.restockService = restockService;
    }

    @PostMapping
    public ResponseEntity<RestockResponse> newRestock(@RequestBody RestockRequest restockRequest) {
        return new ResponseEntity<>(restockService.restockByProductId(restockRequest), new HttpHeaders(), HttpStatus.CREATED);
    }

    @PostMapping("/removestock")
    public ResponseEntity<SellResponse> removeRestock(@RequestBody ProductsSellRequest productsSellRequest) {
        return new ResponseEntity<>(restockService.removeStockByProductId(productsSellRequest), new HttpHeaders(), HttpStatus.CREATED);
    }

    @GetMapping("/productbybarcode/{barcode}")
    public ResponseEntity<ProductsResponse> findProductByBarcodeWithQuantity(@PathVariable String barcode) {
        ProductsResponse productsResponse = restockService.findProductByBarcodeWithQuantity(barcode);
        if (productsResponse != null)
            return new ResponseEntity<>(productsResponse, new HttpHeaders(), HttpStatus.OK);
        else
            return new ResponseEntity<>(null, new HttpHeaders(), HttpStatus.NOT_FOUND);
    }
}
