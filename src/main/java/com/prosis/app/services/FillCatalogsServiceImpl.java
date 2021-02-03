package com.prosis.app.services;

import com.prosis.app.DAOs.*;
import com.prosis.app.DTOs.*;
import com.prosis.app.entities.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FillCatalogsServiceImpl implements FillCatalogsService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final PersonRepository personRepository;
    private final ModelMapper modelMapper;

    @PersistenceContext
    private final EntityManager entityManager;

    @Autowired
    public FillCatalogsServiceImpl(ProductRepository productRepository, CategoryRepository categoryRepository, PersonRepository personRepository, ModelMapper modelMapper, EntityManager entityManager) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.personRepository = personRepository;
        this.modelMapper = modelMapper;
        this.entityManager = entityManager;
    }

    @Override
    public List<PersonDTO> findAllPersonClients() {
        return personRepository.findAllByKind((short) 1).stream().map(this::convertPersonToDto).collect(Collectors.toList());
    }

    @Override
    public PersonDTO savePerson(PersonDTO personDTO) {
        PersonEntity personEntity = convertPersonToEntity(personDTO);
        return convertPersonToDto(personRepository.save(personEntity));
    }

    @Override
    public List<CategoriesResponse> findAllCategoryWithProductsCount() {
        return categoryRepository.findAllWithProductsCount();
    }

    @Override
    public CategoryDTO saveCategory(CategoryDTO categoryDTO) {
        CategoryEntity categoryEntity = convertCategoryToEntity(categoryDTO);
        return convertCategoryToDto(categoryRepository.save(categoryEntity));
    }

    @Override
    public List<ProductsResponse> findAllProductWithSumQuantity() {
        return productRepository.findAllWithSumQuantity();
    }

    @Override
    public ProductDTO saveProduct(ProductDTO productDTO) {
        ProductEntity productEntity = convertProductToEntity(productDTO);
        return convertProductToDto(productRepository.save(productEntity));
    }

    public ProductEntity convertProductToEntity(ProductDTO productDTO) {
        ProductEntity productEntity = modelMapper.map(productDTO, ProductEntity.class);
        UserEntity userEntity = entityManager.getReference(UserEntity.class, productDTO.getUserId());
        CategoryEntity categoryEntity = entityManager.getReference(CategoryEntity.class, productDTO.getCategoryId());
        productEntity.setUserByUserId(userEntity);
        productEntity.setCategoryByCategoryId(categoryEntity);
        return productEntity;
    }

    public ProductDTO convertProductToDto(ProductEntity productEntity) {
        ProductDTO productDTO = modelMapper.map(productEntity, ProductDTO.class);
        productDTO.setCreatedAtConverted(productEntity.getCreatedAt(), "America/Mexico_City");
        productDTO.setUpdatedAtConverted(productEntity.getUpdatedAt(), "America/Mexico_City");
        return productDTO;
    }

    public PersonEntity convertPersonToEntity(PersonDTO personDTO) {
        return modelMapper.map(personDTO, PersonEntity.class);
    }

    public PersonDTO convertPersonToDto(PersonEntity personEntity) {
        PersonDTO personDTO = modelMapper.map(personEntity, PersonDTO.class);
        personDTO.setCreatedAtConverted(personEntity.getCreatedAt(), "America/Mexico_City");
        return personDTO;
    }

    public CategoryEntity convertCategoryToEntity(CategoryDTO categoryDTO) {
        return modelMapper.map(categoryDTO, CategoryEntity.class);
    }

    public CategoryDTO convertCategoryToDto(CategoryEntity categoryEntity) {
        CategoryDTO categoryDTO = modelMapper.map(categoryEntity, CategoryDTO.class);
        categoryDTO.setCreatedAtConverted(categoryEntity.getCreatedAt(), "America/Mexico_City");
        return categoryDTO;
    }
}
