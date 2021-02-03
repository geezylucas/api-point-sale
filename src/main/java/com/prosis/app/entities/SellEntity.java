package com.prosis.app.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Objects;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = SellEntity.class)
@Entity
@Table(name = "sell", schema = "inventorylite")
public class SellEntity {
    private int id;
    private Integer personId;
    private Short operationTypeId;
    private Integer userId;
    private Integer boxId;
    private BigDecimal total;
    private BigDecimal cash;
    private Double discount;
    private boolean status;
    private Timestamp createdAt;
    private Collection<OperationEntity> operationsById;
    private PersonEntity personByPersonId;
    private OperationTypeEntity operationTypeByOperationTypeId;
    private UserEntity userByUserId;
    private BoxEntity boxByBoxId;

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
    @Column(name = "person_id", nullable = true, insertable = false, updatable = false)
    public Integer getPersonId() {
        return personId;
    }

    public void setPersonId(Integer personId) {
        this.personId = personId;
    }

    @Basic
    @Column(name = "operation_type_id", nullable = true, insertable = false, updatable = false)
    public Short getOperationTypeId() {
        return operationTypeId;
    }

    public void setOperationTypeId(Short operationTypeId) {
        this.operationTypeId = operationTypeId;
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
    @Column(name = "box_id", nullable = true, insertable = false, updatable = false)
    public Integer getBoxId() {
        return boxId;
    }

    public void setBoxId(Integer boxId) {
        this.boxId = boxId;
    }

    @Basic
    @Column(name = "total", nullable = false, precision = 2)
    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    @Basic
    @Column(name = "cash", nullable = true, precision = 2)
    public BigDecimal getCash() {
        return cash;
    }

    public void setCash(BigDecimal cash) {
        this.cash = cash;
    }

    @Basic
    @Column(name = "discount", nullable = true, precision = 0)
    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    @Basic
    @Column(name = "status", nullable = false)
    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Basic
    @CreationTimestamp
    @Column(name = "created_at", nullable = false)
    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SellEntity that = (SellEntity) o;
        return id == that.id && status == that.status && Objects.equals(personId, that.personId) && Objects.equals(operationTypeId, that.operationTypeId) && Objects.equals(userId, that.userId) && Objects.equals(boxId, that.boxId) && Objects.equals(total, that.total) && Objects.equals(cash, that.cash) && Objects.equals(discount, that.discount) && Objects.equals(createdAt, that.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, personId, operationTypeId, userId, boxId, total, cash, discount, status, createdAt);
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "sellBySellId")
    public Collection<OperationEntity> getOperationsById() {
        return operationsById;
    }

    public void setOperationsById(Collection<OperationEntity> operationsById) {
        this.operationsById = operationsById;
    }

    @ManyToOne
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    public PersonEntity getPersonByPersonId() {
        return personByPersonId;
    }

    public void setPersonByPersonId(PersonEntity personByPersonId) {
        this.personByPersonId = personByPersonId;
    }

    @ManyToOne
    @JoinColumn(name = "operation_type_id", referencedColumnName = "id")
    public OperationTypeEntity getOperationTypeByOperationTypeId() {
        return operationTypeByOperationTypeId;
    }

    public void setOperationTypeByOperationTypeId(OperationTypeEntity operationTypeByOperationTypeId) {
        this.operationTypeByOperationTypeId = operationTypeByOperationTypeId;
    }

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    public UserEntity getUserByUserId() {
        return userByUserId;
    }

    public void setUserByUserId(UserEntity userByUserId) {
        this.userByUserId = userByUserId;
    }

    @ManyToOne
    @JoinColumn(name = "box_id", referencedColumnName = "id")
    public BoxEntity getBoxByBoxId() {
        return boxByBoxId;
    }

    public void setBoxByBoxId(BoxEntity boxByBoxId) {
        this.boxByBoxId = boxByBoxId;
    }
}
