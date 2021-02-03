package com.prosis.app.DAOs;

import com.prosis.app.entities.BoxEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoxRepository extends JpaRepository<BoxEntity, Integer> {

}
