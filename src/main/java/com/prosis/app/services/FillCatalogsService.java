package com.prosis.app.services;

import com.prosis.app.DTOs.*;

import java.util.List;

public interface FillCatalogsService {
    List<PersonDTO> findAllPersonClients();

    PersonDTO savePerson(PersonDTO personDTO);

    List<CategoriesResponse> findAllCategoryWithProductsCount();

    CategoryDTO saveCategory(CategoryDTO categoryDTO);

    List<ProductsResponse> findAllProductWithSumQuantity();

    ProductDTO saveProduct(ProductDTO productDTO);
}
