package com.prosis.app.DAOs;

import com.prosis.app.DTOs.CategoriesResponse;
import com.prosis.app.entities.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, Integer> {
    @Query(nativeQuery = true, value = "select c.id as id , c.name as name , c.description as description, COUNT(p.category_id) as products " +
            "from category c " +
            "left join product p on c.id = p.category_id " +
            "group by c.id, c.name, c.description;")
    List<CategoriesResponse> findAllWithProductsCount();
}
