package com.prosis.app.DAOs;

import com.prosis.app.DTOs.DetailReturnResponse;
import com.prosis.app.entities.OperationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OperationRepository extends JpaRepository<OperationEntity, Integer> {
    @Query(nativeQuery = true, value = "select o.id                     as id, " +
            "       p.name                   as productName, " +
            "       o.quantity               as quantity, " +
            "       o.price_out              as priceOut, " +
            "       o.quantity * o.price_out as total " +
            "from operation o " +
            "         inner join product p on o.product_id = p.id " +
            "where o.sell_id = ?1 ")
    List<DetailReturnResponse> findAllWithAmountWhereSellId(Integer sellId);
}
