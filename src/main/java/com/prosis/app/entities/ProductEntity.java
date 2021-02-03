package com.prosis.app.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Objects;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = ProductEntity.class)
@Entity
@Table(name = "product", schema = "inventorylite")
public class ProductEntity {
    private int id;
    private String image;
    private String barcode;
    private String name;
    private String description;
    private int inventoryMin;
    private BigDecimal priceIn;
    private BigDecimal priceOut1;
    private BigDecimal priceOut2;
    private BigDecimal priceOut3;
    private Double inventoryOut3;
    private String unit;
    private String presentation;
    private boolean status;
    private boolean isBulk;
    private Integer userId;
    private Integer categoryId;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private Collection<OperationEntity> operationsById;
    private UserEntity userByUserId;
    private CategoryEntity categoryByCategoryId;
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
    @Column(name = "image", nullable = true, length = 255)
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Basic
    @Column(name = "barcode", nullable = false, length = 50)
    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    @Basic
    @Column(name = "name", nullable = false, length = 255)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
    @Column(name = "inventory_min", nullable = false)
    public int getInventoryMin() {
        return inventoryMin;
    }

    public void setInventoryMin(int inventoryMin) {
        this.inventoryMin = inventoryMin;
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
    @Column(name = "price_out1", nullable = false, precision = 2)
    public BigDecimal getPriceOut1() {
        return priceOut1;
    }

    public void setPriceOut1(BigDecimal priceOut1) {
        this.priceOut1 = priceOut1;
    }

    @Basic
    @Column(name = "price_out2", nullable = true, precision = 2)
    public BigDecimal getPriceOut2() {
        return priceOut2;
    }

    public void setPriceOut2(BigDecimal priceOut2) {
        this.priceOut2 = priceOut2;
    }

    @Basic
    @Column(name = "price_out3", nullable = true, precision = 2)
    public BigDecimal getPriceOut3() {
        return priceOut3;
    }

    public void setPriceOut3(BigDecimal priceOut3) {
        this.priceOut3 = priceOut3;
    }

    @Basic
    @Column(name = "inventory_out3", nullable = true, precision = 0)
    public Double getInventoryOut3() {
        return inventoryOut3;
    }

    public void setInventoryOut3(Double inventoryOut3) {
        this.inventoryOut3 = inventoryOut3;
    }

    @Basic
    @Column(name = "unit", nullable = true, length = 255)
    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    @Basic
    @Column(name = "presentation", nullable = true, length = 255)
    public String getPresentation() {
        return presentation;
    }

    public void setPresentation(String presentation) {
        this.presentation = presentation;
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
    @Column(name = "is_bulk", nullable = false)
    public boolean isBulk() {
        return isBulk;
    }

    public void setBulk(boolean bulk) {
        isBulk = bulk;
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
    @Column(name = "category_id", nullable = true, insertable = false, updatable = false)
    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
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

    @Basic
    @UpdateTimestamp
    @Column(name = "updated_at", nullable = true)
    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductEntity that = (ProductEntity) o;
        return id == that.id && inventoryMin == that.inventoryMin && status == that.status && isBulk == that.isBulk && Objects.equals(image, that.image) && Objects.equals(barcode, that.barcode) && Objects.equals(name, that.name) && Objects.equals(description, that.description) && Objects.equals(priceIn, that.priceIn) && Objects.equals(priceOut1, that.priceOut1) && Objects.equals(priceOut2, that.priceOut2) && Objects.equals(priceOut3, that.priceOut3) && Objects.equals(inventoryOut3, that.inventoryOut3) && Objects.equals(unit, that.unit) && Objects.equals(presentation, that.presentation) && Objects.equals(userId, that.userId) && Objects.equals(categoryId, that.categoryId) && Objects.equals(createdAt, that.createdAt) && Objects.equals(updatedAt, that.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, image, barcode, name, description, inventoryMin, priceIn, priceOut1, priceOut2, priceOut3, inventoryOut3, unit, presentation, status, isBulk, userId, categoryId, createdAt, updatedAt);
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "productByProductId")
    public Collection<OperationEntity> getOperationsById() {
        return operationsById;
    }

    public void setOperationsById(Collection<OperationEntity> operationsById) {
        this.operationsById = operationsById;
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
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    public CategoryEntity getCategoryByCategoryId() {
        return categoryByCategoryId;
    }

    public void setCategoryByCategoryId(CategoryEntity categoryByCategoryId) {
        this.categoryByCategoryId = categoryByCategoryId;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "productByProductId")
    public Collection<ProductBatchEntity> getProductBatchesById() {
        return productBatchesById;
    }

    public void setProductBatchesById(Collection<ProductBatchEntity> productBatchesById) {
        this.productBatchesById = productBatchesById;
    }
}
