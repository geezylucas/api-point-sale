package com.prosis.app.DAOs;

import com.prosis.app.entities.ProductBatchEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductBatchRepository extends JpaRepository<ProductBatchEntity, Integer> {
    // TODO: change with order by
    @Query(nativeQuery = true, value = "select * " +
            "from product_batch " +
            "where product_id = ?1 " +
            "order by date_expiry is null, date_expiry;")
    List<ProductBatchEntity> findAllProductBatchByProductIdOrderDateExpiry(int productByProductId);
}
