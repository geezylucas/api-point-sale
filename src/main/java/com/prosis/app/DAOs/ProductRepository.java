package com.prosis.app.DAOs;

import com.prosis.app.DTOs.ProductsResponse;
import com.prosis.app.entities.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Integer> {
    @Query(nativeQuery = true, value = "select p.id, p.barcode, p.name, p.image, p.description, p.inventory_min as inventoryMin, p.price_in as priceIn, p.price_out1 as priceOut1, p.price_out2 as priceOut2, p.price_out3 as priceOut3, " +
            "p.unit, p.presentation, p.status as status, p.is_bulk as bulk, c.name as category, p.inventory_out3 as inventoryOut3, SUM(pb.quantity) as stock " +
            "from product p " +
            "left join product_batch pb on p.id = pb.product_id " +
            "inner join category c on p.category_id = c.id " +
            "where p.barcode = :barcode " +
            "group by p.id;")
    Optional<ProductsResponse> findBarcodeWithQuantity(@Param("barcode") String barcode);

    // TODO: add quantity1 for products bulk
    @Query(nativeQuery = true, value = "select p.id, p.barcode, p.name, p.image, p.description, p.inventory_min as inventoryMin, p.price_in as priceIn, p.price_out1 as priceOut1, p.price_out2 as priceOut2, p.price_out3 as priceOut3, " +
            "p.unit, p.presentation, p.status as status, p.is_bulk as bulk, c.name as category, p.inventory_out3 as inventoryOut3, SUM(pb.quantity) as stock " +
            "from product p " +
            "left join product_batch pb on p.id = pb.product_id " +
            "inner join category c on p.category_id = c.id " +
            "group by p.id;")
    List<ProductsResponse> findAllWithSumQuantity();
}
