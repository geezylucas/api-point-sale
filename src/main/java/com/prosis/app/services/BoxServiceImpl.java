package com.prosis.app.services;

import com.prosis.app.DAOs.BoxRepository;
import com.prosis.app.DAOs.SellRepository;
import com.prosis.app.DAOs.UserRepository;
import com.prosis.app.DTOs.CloseShiftRequest;
import com.prosis.app.DTOs.TotalOperations;
import com.prosis.app.entities.BoxEntity;
import com.prosis.app.entities.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Optional;

@Service
public class BoxServiceImpl implements BoxService {
    private final UserRepository userRepository;
    private final BoxRepository boxRepository;
    private final SellRepository sellRepository;

    @Autowired
    public BoxServiceImpl(UserRepository userRepository, BoxRepository boxRepository, SellRepository sellRepository) {
        this.userRepository = userRepository;
        this.boxRepository = boxRepository;
        this.sellRepository = sellRepository;
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

    @Override
    public BoxEntity closeShift(CloseShiftRequest closeShiftRequest) {
        TotalOperations operations = sellRepository.findTotalsWhereBoxId(closeShiftRequest.getBoxId());
        Optional<BoxEntity> boxEntity = boxRepository.findById(closeShiftRequest.getBoxId());
        if (boxEntity.isPresent() && operations != null) {
            boxEntity.get().setCash(closeShiftRequest.getCash());
            Calendar calendar = Calendar.getInstance();
            boxEntity.get().setCreatedEnd(new Timestamp(calendar.getTimeInMillis()));
            boxEntity.get().setTotal(operations.getTotalMoney());
            boxEntity.get().setQuantity(operations.getTotalProducts());
            boxEntity.get().setDescription(closeShiftRequest.getComment());

            return boxRepository.save(boxEntity.get());
        }

        return null;
    }
}
