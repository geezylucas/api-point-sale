package com.prosis.app.services;

import com.prosis.app.DAOs.OperationRepository;
import com.prosis.app.DAOs.ProductBatchRepository;
import com.prosis.app.DAOs.ProductRepository;
import com.prosis.app.DAOs.SellRepository;
import com.prosis.app.DTOs.*;
import com.prosis.app.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class SellServiceImpl implements SellService {
    private final SellRepository sellRepository;
    private final OperationRepository operationRepository;
    private final ProductBatchRepository productBatchRepository;
    private final ProductRepository productRepository;

    @PersistenceContext
    private final EntityManager entityManager;

    @Autowired
    public SellServiceImpl(SellRepository sellRepository, OperationRepository operationRepository, ProductBatchRepository productBatchRepository, ProductRepository productRepository, EntityManager entityManager) {
        this.sellRepository = sellRepository;
        this.operationRepository = operationRepository;
        this.productBatchRepository = productBatchRepository;
        this.productRepository = productRepository;
        this.entityManager = entityManager;
    }

    @Override
    public SellResponse sellProducts(SellRequest sellRequest) {
        // first save new sell and
        PersonEntity personEntity = entityManager.getReference(PersonEntity.class, sellRequest.getPersonId());
        UserEntity userEntity = entityManager.getReference(UserEntity.class, sellRequest.getUserId());
        OperationTypeEntity operationTypeEntity = entityManager.getReference(OperationTypeEntity.class, (short) 2);
        BoxEntity boxEntity = entityManager.getReference(BoxEntity.class, sellRequest.getBoxId());

        // Save new Sell
        SellEntity sellEntity = new SellEntity();
        sellEntity.setTotal(sellRequest.getTotal());
        sellEntity.setCash(sellRequest.getCash());
        sellEntity.setPersonByPersonId(personEntity);
        sellEntity.setOperationTypeByOperationTypeId(operationTypeEntity);
        sellEntity.setUserByUserId(userEntity);
        sellEntity.setBoxByBoxId(boxEntity);
        sellEntity.setStatus(true);

        SellEntity newSellEntity = sellRepository.saveAndFlush(sellEntity);

        // for loop to products list in request and generate operation then discount quantity in product batch
        for (ProductsSellRequest product : sellRequest.getProducts()) {
            Optional<ProductEntity> productEntity = productRepository.findById(product.getProductId());
            if (productEntity.isPresent()) {
                // TODO: add quantity1 for products bulk
                List<ProductBatchEntity> batchEntityList = productBatchRepository.findAllProductBatchByProductIdOrderDateExpiry(productEntity.get().getId());
                for (ProductBatchEntity productBatch : batchEntityList) {
                    if (product.getQuantity() > 0) {
                        double currentQuantity = productBatch.getQuantity();
                        int saleQuantity = product.getQuantity();

                        // create operation
                        OperationEntity operationEntity = new OperationEntity();
                        operationEntity.setProductByProductId(productEntity.get());
                        operationEntity.setOperationTypeByOperationTypeId(operationTypeEntity);
                        operationEntity.setSellBySellId(newSellEntity);
                        operationEntity.setStatus(true);
                        // TODO: get price by criterion
                        if (dynamicDate() && productEntity.get().getPriceOut2() != null) {
                            operationEntity.setPriceOut(productEntity.get().getPriceOut2());
                        } else {
                            operationEntity.setPriceOut(productEntity.get().getPriceOut1());
                        }
                        operationEntity.setPriceIn(productBatch.getPriceIn());
                        operationEntity.setDateExpiry(productBatch.getDateExpiry());

                        if (currentQuantity >= saleQuantity) {
                            operationEntity.setQuantity(saleQuantity);
                            productBatch.setQuantity(currentQuantity - saleQuantity);
                            product.setQuantity(0);
                        } else {
                            operationEntity.setQuantity((int) currentQuantity);
                            productBatch.setQuantity(0.0);
                            product.setQuantity((int) (saleQuantity - currentQuantity));
                        }

                        operationRepository.save(operationEntity);

                        if (productBatch.getQuantity() == 0) {
                            productBatchRepository.delete(productBatch);
                        } else {
                            productBatchRepository.save(productBatch);
                        }
                    }
                }
            }
        }

        return new SellResponse("Ok");
    }

    @Override
    public List<MovementsResponse> getMovementsAllWithQuantity() {
        return sellRepository.findAllWithQuantity();
    }

    @Override
    public List<ReturnsResponse> getReturnsToday() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        return sellRepository.findAllReturnsToday(dateFormat.format(date));
    }

    @Override
    public List<DetailReturnResponse> getDetailsReturn(int sellId) {
        return operationRepository.findAllWithAmountWhereSellId(sellId);
    }

    public boolean dynamicDate() {
        ZoneId zoneId = ZoneId.of("America/Mexico_City");
        LocalDateTime todayWithTime = LocalDateTime.now(zoneId);
        LocalDate today = LocalDate.now(zoneId);

        String timeStart = " 23:00";
        String timeEnd = " 07:00";

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime dateTimeStart = LocalDateTime.parse(today + timeStart, formatter);
        LocalDateTime dateTimeEnd = LocalDateTime.parse(today + timeEnd, formatter);

        return (!todayWithTime.isBefore(dateTimeStart)) && (todayWithTime.isBefore(dateTimeEnd));
    }
}
