package com.prosis.app.controllers;

import com.prosis.app.DTOs.*;
import com.prosis.app.services.FillCatalogsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/fillcatalogs")
public class FillCatalogsController {
    private final FillCatalogsService fillCatalogsService;

    @Autowired
    public FillCatalogsController(FillCatalogsService fillCatalogsService) {
        this.fillCatalogsService = fillCatalogsService;
    }

    @GetMapping("/clients")
    public ResponseEntity<?> allPerson() {
        List<PersonDTO> personDTOList = fillCatalogsService.findAllPersonClients();
        Map<String, Object> response = new HashMap<>();
        response.put("data", personDTOList);
        response.put("total", personDTOList.size());
        return new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.OK);
    }

    @PostMapping("/persons")
    public ResponseEntity<PersonDTO> savePerson(@RequestBody PersonDTO newPerson) {
        return new ResponseEntity<>(fillCatalogsService.savePerson(newPerson), new HttpHeaders(), HttpStatus.CREATED);
    }

    @GetMapping("/categories")
    public ResponseEntity<?> allCategoryWithProductsCount() {
        List<CategoriesResponse> categoriesResponses = fillCatalogsService.findAllCategoryWithProductsCount();
        Map<String, Object> response = new HashMap<>();
        response.put("data", categoriesResponses);
        response.put("total", categoriesResponses.size());
        return new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.OK);
    }

    @PostMapping("/categories")
    public ResponseEntity<CategoryDTO> saveCategory(@RequestBody CategoryDTO newCategory) {
        return new ResponseEntity<>(fillCatalogsService.saveCategory(newCategory), new HttpHeaders(), HttpStatus.CREATED);
    }

    @GetMapping("/products")
    public ResponseEntity<?> allProductWithSumQuantity() {
        List<ProductsResponse> productsResponse = fillCatalogsService.findAllProductWithSumQuantity();
        Map<String, Object> response = new HashMap<>();
        response.put("data", productsResponse);
        response.put("total", productsResponse.size());
        return new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.OK);
    }

    @PostMapping("/products")
    public ResponseEntity<ProductDTO> saveProduct(@RequestBody ProductDTO newProduct) {
        return new ResponseEntity<>(fillCatalogsService.saveProduct(newProduct), new HttpHeaders(), HttpStatus.CREATED);
    }
}
