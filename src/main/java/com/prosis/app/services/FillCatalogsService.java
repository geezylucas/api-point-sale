package com.prosis.app.services;

import com.prosis.app.DTOs.*;

import java.util.List;
import java.util.Optional;

public interface FillCatalogsService {
    List<PersonDTO> findAllPersonClients();

    PersonDTO savePerson(PersonDTO personDTO);

    List<CategoriesResponse> findAllCategoryWithProductsCount();

    CategoryDTO saveCategory(CategoryDTO categoryDTO);

    List<ProductsResponse> findAllProductWithSumQuantity();

    ProductDTO saveProduct(ProductDTO productDTO);

    Optional<ProductDTO> updateProduct(ProductDTO productDTO, int id);

    void deleteProduct(int id);

    Optional<PersonDTO> findClientById(int id);
}
