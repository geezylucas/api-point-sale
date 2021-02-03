package com.prosis.app.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Objects;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = ProductBatchEntity.class)
@Entity
@Table(name = "product_batch", schema = "inventorylite")
public class ProductBatchEntity {
    private int id;
    private Integer operationId;
    private Integer productId;
    private BigDecimal priceIn;
    private double quantity;
    private Timestamp dateExpiry;
    private Timestamp createdAt;
    private OperationEntity operationByOperationId;
    private ProductEntity productByProductId;

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
    @Column(name = "operation_id", nullable = true, insertable = false, updatable = false)
    public Integer getOperationId() {
        return operationId;
    }

    public void setOperationId(Integer operationId) {
        this.operationId = operationId;
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
    @Column(name = "price_in", nullable = false, precision = 2)
    public BigDecimal getPriceIn() {
        return priceIn;
    }

    public void setPriceIn(BigDecimal priceIn) {
        this.priceIn = priceIn;
    }

    @Basic
    @Column(name = "quantity", nullable = false, precision = 0)
    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
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
    @Column(name = "created_at", nullable = false)
    @CreationTimestamp
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
        ProductBatchEntity that = (ProductBatchEntity) o;
        return id == that.id && Double.compare(that.quantity, quantity) == 0 && Objects.equals(operationId, that.operationId) && Objects.equals(productId, that.productId) && Objects.equals(priceIn, that.priceIn) && Objects.equals(dateExpiry, that.dateExpiry) && Objects.equals(createdAt, that.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, operationId, productId, priceIn, quantity, dateExpiry, createdAt);
    }

    @ManyToOne
    @JoinColumn(name = "operation_id", referencedColumnName = "id")
    public OperationEntity getOperationByOperationId() {
        return operationByOperationId;
    }

    public void setOperationByOperationId(OperationEntity operationByOperationId) {
        this.operationByOperationId = operationByOperationId;
    }

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    public ProductEntity getProductByProductId() {
        return productByProductId;
    }

    public void setProductByProductId(ProductEntity productByProductId) {
        this.productByProductId = productByProductId;
    }
}
