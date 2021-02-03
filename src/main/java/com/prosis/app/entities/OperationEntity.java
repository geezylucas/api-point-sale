package com.prosis.app.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Objects;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = OperationEntity.class)
@Entity
@Table(name = "operation", schema = "inventorylite")
public class OperationEntity {
    private int id;
    private Integer productId;
    private Short operationTypeId;
    private Integer sellId;
    private int quantity;
    private Double quantity1;
    private BigDecimal priceIn;
    private BigDecimal priceOut;
    private Timestamp dateExpiry;
    private boolean status;
    private Timestamp createdAt;
    private ProductEntity productByProductId;
    private OperationTypeEntity operationTypeByOperationTypeId;
    private SellEntity sellBySellId;
    private Collection<ProductBatchEntity> productBatchesById;

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
    @Column(name = "product_id", nullable = true, insertable = false, updatable = false)
    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
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
    @Column(name = "sell_id", nullable = true, insertable = false, updatable = false)
    public Integer getSellId() {
        return sellId;
    }

    public void setSellId(Integer sellId) {
        this.sellId = sellId;
    }

    @Basic
    @Column(name = "quantity", nullable = false)
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Basic
    @Column(name = "quantity1", nullable = true, precision = 0)
    public Double getQuantity1() {
        return quantity1;
    }

    public void setQuantity1(Double quantity1) {
        this.quantity1 = quantity1;
    }

    @Basic
    @Column(name = "price_in", nullable = false, precision = 2)
    public BigDecimal getPriceIn() {
        return priceIn;
    }

    public void setPriceIn(BigDecimal priceIn) {
        this.priceIn = priceIn;
    }

    @Basic
    @Column(name = "price_out", nullable = true, precision = 2)
    public BigDecimal getPriceOut() {
        return priceOut;
    }

    public void setPriceOut(BigDecimal priceOut) {
        this.priceOut = priceOut;
    }

    @Basic
    @Column(name = "date_expiry", nullable = true)
    public Timestamp getDateExpiry() {
        return dateExpiry;
    }

    public void setDateExpiry(Timestamp dateExpiry) {
        this.dateExpiry = dateExpiry;
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
        OperationEntity that = (OperationEntity) o;
        return id == that.id && quantity == that.quantity && status == that.status && Objects.equals(productId, that.productId) && Objects.equals(operationTypeId, that.operationTypeId) && Objects.equals(sellId, that.sellId) && Objects.equals(quantity1, that.quantity1) && Objects.equals(priceIn, that.priceIn) && Objects.equals(priceOut, that.priceOut) && Objects.equals(dateExpiry, that.dateExpiry) && Objects.equals(createdAt, that.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, productId, operationTypeId, sellId, quantity, quantity1, priceIn, priceOut, dateExpiry, status, createdAt);
    }

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    public ProductEntity getProductByProductId() {
        return productByProductId;
    }

    public void setProductByProductId(ProductEntity productByProductId) {
        this.productByProductId = productByProductId;
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
    @JoinColumn(name = "sell_id", referencedColumnName = "id")
    public SellEntity getSellBySellId() {
        return sellBySellId;
    }

    public void setSellBySellId(SellEntity sellBySellId) {
        this.sellBySellId = sellBySellId;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "operationByOperationId")
    public Collection<ProductBatchEntity> getProductBatchesById() {
        return productBatchesById;
    }

    public void setProductBatchesById(Collection<ProductBatchEntity> productBatchesById) {
        this.productBatchesById = productBatchesById;
    }
}
