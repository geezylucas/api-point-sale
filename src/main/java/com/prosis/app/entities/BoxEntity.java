package com.prosis.app.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Objects;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = BoxEntity.class)
@Entity
@Table(name = "box", schema = "inventorylite")
public class BoxEntity {
    private int id;
    private Integer userId;
    private BigDecimal cashInBox;
    private String description;
    private Integer quantity;
    private BigDecimal total;
    private Timestamp createdStart;
    private Timestamp createdEnd;
    private UserEntity userByUserId;
    private Collection<SellEntity> sellsById;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "user_id", nullable = true, insertable = false, updatable = false)
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "cash_in_box", nullable = false, precision = 2)
    public BigDecimal getCashInBox() {
        return cashInBox;
    }

    public void setCashInBox(BigDecimal cashInBox) {
        this.cashInBox = cashInBox;
    }

    @Basic
    @Column(name = "description", nullable = true, length = -1)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "quantity", nullable = true)
    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Basic
    @Column(name = "total", nullable = true, precision = 2)
    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    @Basic
    @CreationTimestamp
    @Column(name = "created_start", nullable = false)
    public Timestamp getCreatedStart() {
        return createdStart;
    }

    public void setCreatedStart(Timestamp createdStart) {
        this.createdStart = createdStart;
    }

    @Basic
    @Column(name = "created_end", nullable = true)
    public Timestamp getCreatedEnd() {
        return createdEnd;
    }

    public void setCreatedEnd(Timestamp createdEnd) {
        this.createdEnd = createdEnd;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BoxEntity boxEntity = (BoxEntity) o;
        return id == boxEntity.id && Objects.equals(userId, boxEntity.userId) && Objects.equals(cashInBox, boxEntity.cashInBox) && Objects.equals(description, boxEntity.description) && Objects.equals(quantity, boxEntity.quantity) && Objects.equals(total, boxEntity.total) && Objects.equals(createdStart, boxEntity.createdStart) && Objects.equals(createdEnd, boxEntity.createdEnd);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, cashInBox, description, quantity, total, createdStart, createdEnd);
    }

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    public UserEntity getUserByUserId() {
        return userByUserId;
    }

    public void setUserByUserId(UserEntity userByUserId) {
        this.userByUserId = userByUserId;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "boxByBoxId")
    public Collection<SellEntity> getSellsById() {
        return sellsById;
    }

    public void setSellsById(Collection<SellEntity> sellsById) {
        this.sellsById = sellsById;
    }
}
