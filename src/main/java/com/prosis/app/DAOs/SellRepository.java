package com.prosis.app.DAOs;

import com.prosis.app.DTOs.MovementsResponse;
import com.prosis.app.DTOs.ReturnsResponse;
import com.prosis.app.DTOs.TotalOperations;
import com.prosis.app.entities.SellEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SellRepository extends JpaRepository<SellEntity, Integer> {
    // TODO: add quantity1
    @Query(nativeQuery = true, value = "select s.id as id, s.created_at as createdSell, p.name as productName, " +
            "ot.name as operation, o.quantity as quantity, u.username as cashier, c.name as category " +
            "from sell s " +
            "         inner join operation o on s.id = o.sell_id " +
            "         inner join product p on o.product_id = p.id " +
            "         inner join operation_type ot on s.operation_type_id = ot.id " +
            "         inner join user u on s.user_id = u.id " +
            "         inner join category c on p.category_id = c.id " +
            "order by s.created_at desc;")
    List<MovementsResponse> findAllWithQuantity();

    @Query(nativeQuery = true, value = "select s.id                                  as folio, " +
            "       sum(o.quantity)                       as products, " +
            "       s.total                               as total, " +
            "       s.cash                                as cash, " +
            "       DATE_FORMAT(s.created_at, '%H:%i:%s') as time, " +
            "       u.username                            as cashier, " +
            "       CONCAT(p.name, ' ', p.lastname)       as client," +
            "       if(s.status, 'Vigente', 'Cancelada')  as status " +
            "from sell s " +
            "         inner join operation o on s.id = o.sell_id " +
            "         inner join user u on s.user_id = u.id " +
            "         inner join person p on s.person_id = p.id " +
            "where s.operation_type_id = 2 " +
            "  and DATE_FORMAT(s.created_at, '%Y-%m-%d') = ?1 " +
            "group by s.id, s.created_at " +
            "order by s.created_at desc;")
    List<ReturnsResponse> findAllReturnsToday(String date);

    @Query(nativeQuery = true, value = "select sum(s.cash) as totalMoney, sum(o.quantity) as totalProducts " +
            "from sell s " +
            "inner join operation o on s.id = o.sell_id " +
            "where s.operation_type_id = 2 and box_id = ?1 ")
    TotalOperations findTotalsWhereBoxId(int boxId);
}
