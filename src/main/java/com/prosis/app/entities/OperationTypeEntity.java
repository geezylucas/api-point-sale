package com.prosis.app.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = OperationTypeEntity.class)
@Entity
@Table(name = "operation_type", schema = "inventorylite")
public class OperationTypeEntity {
    private short id;
    private String name;
    private Collection<OperationEntity> operationsById;
    private Collection<SellEntity> sellsById;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public short getId() {
        return id;
    }

    public void setId(short id) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OperationTypeEntity that = (OperationTypeEntity) o;
        return id == that.id && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "operationTypeByOperationTypeId")
    public Collection<OperationEntity> getOperationsById() {
        return operationsById;
    }

    public void setOperationsById(Collection<OperationEntity> operationsById) {
        this.operationsById = operationsById;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "operationTypeByOperationTypeId")
    public Collection<SellEntity> getSellsById() {
        return sellsById;
    }

    public void setSellsById(Collection<SellEntity> sellsById) {
        this.sellsById = sellsById;
    }
}
