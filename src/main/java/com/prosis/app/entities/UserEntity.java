package com.prosis.app.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Objects;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = UserEntity.class)
@Entity
@Table(name = "user", schema = "inventorylite")
public class UserEntity {
    private int id;
    private String name;
    private String lastname;
    private String username;
    private String email;
    private String password;
    private String image;
    private boolean status;
    private String role;
    private Timestamp createdAt;
    private Collection<BoxEntity> boxesById;
    private Collection<ProductEntity> productsById;
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
    @Column(name = "name", nullable = false, length = 50)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "lastname", nullable = false, length = 50)
    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    @Basic
    @Column(name = "username", nullable = false, length = 50)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "email", nullable = false, length = 255)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "password", nullable = false, length = 60)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
    @Column(name = "status", nullable = false)
    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Basic
    @Column(name = "role", nullable = false, length = 50)
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
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
        UserEntity that = (UserEntity) o;
        return id == that.id && status == that.status && Objects.equals(name, that.name) && Objects.equals(lastname, that.lastname) && Objects.equals(username, that.username) && Objects.equals(email, that.email) && Objects.equals(password, that.password) && Objects.equals(image, that.image) && Objects.equals(role, that.role) && Objects.equals(createdAt, that.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, lastname, username, email, password, image, status, role, createdAt);
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "userByUserId")
    public Collection<BoxEntity> getBoxesById() {
        return boxesById;
    }

    public void setBoxesById(Collection<BoxEntity> boxesById) {
        this.boxesById = boxesById;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "userByUserId")
    public Collection<ProductEntity> getProductsById() {
        return productsById;
    }

    public void setProductsById(Collection<ProductEntity> productsById) {
        this.productsById = productsById;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "userByUserId")
    public Collection<SellEntity> getSellsById() {
        return sellsById;
    }

    public void setSellsById(Collection<SellEntity> sellsById) {
        this.sellsById = sellsById;
    }
}
