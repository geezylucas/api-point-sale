package com.prosis.app.services;

import com.prosis.app.DAOs.BoxRepository;
import com.prosis.app.DAOs.UserRepository;
import com.prosis.app.entities.BoxEntity;
import com.prosis.app.entities.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class BoxServiceImpl implements BoxService {
    private final UserRepository userRepository;
    private final BoxRepository boxRepository;

    @Autowired
    public BoxServiceImpl(UserRepository userRepository, BoxRepository boxRepository) {
        this.userRepository = userRepository;
        this.boxRepository = boxRepository;
    }

    @Override
    public BoxEntity saveBox(String username, BigDecimal cashInBox) {
        Optional<UserEntity> userEntity = userRepository.findByUsername(username);
        BoxEntity boxEntity = new BoxEntity();

        if (userEntity.isPresent()) {
            // Create row in box table
            boxEntity.setCashInBox(cashInBox);
            boxEntity.setUserByUserId(userEntity.get());
            boxRepository.saveAndFlush(boxEntity);
        }

        return boxEntity;
    }
}
