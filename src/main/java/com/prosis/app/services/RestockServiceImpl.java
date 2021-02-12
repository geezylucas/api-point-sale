package com.prosis.app.services;

import com.prosis.app.DAOs.*;
import com.prosis.app.DTOs.*;
import com.prosis.app.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
public class RestockServiceImpl implements RestockService {
    private final SellRepository sellRepository;
    private final OperationRepository operationRepository;
    private final ProductBatchRepository productBatchRepository;
    private final ProductRepository productRepository;

    @PersistenceContext
    private final EntityManager entityManager;

    @Autowired
    public RestockServiceImpl(SellRepository sellRepository, OperationRepository operationRepository, ProductBatchRepository productBatchRepository, ProductRepository productRepository, EntityManager entityManager) {
        this.sellRepository = sellRepository;
        this.operationRepository = operationRepository;
        this.productBatchRepository = productBatchRepository;
        this.productRepository = productRepository;
        this.entityManager = entityManager;
    }

    @Override
    public RestockResponse restockByProductId(RestockRequest restockRequest) {
        // Search product by id to get priceIn's value and isBulk's value
        Optional<ProductEntity> productEntity = productRepository.findById(restockRequest.getProductId());

        OperationEntity operationEntity = new OperationEntity();
        ProductBatchEntity productBatchEntity = new ProductBatchEntity();
        BigDecimal total;
        if (productEntity.isPresent()) {
            BigDecimal priceIn = productEntity.get().getPriceIn();
            operationEntity.setPriceIn(priceIn);

            if (productEntity.get().isBulk()) {
                // Calculate the total by multiplying the quantity by the priceIn of the product
                // RULE OF THREE
                BigDecimal multiply = priceIn.multiply(BigDecimal.valueOf(restockRequest.getQuantity()));
                // With grams
                total = multiply.divide(new BigDecimal(1000), RoundingMode.HALF_UP);

                operationEntity.setQuantity(1);
                operationEntity.setQuantity1(restockRequest.getQuantity());

            } else {
                // Calculate the total by multiplying the quantity by the priceIn of the product
                total = priceIn.multiply(BigDecimal.valueOf(restockRequest.getQuantity()));

                operationEntity.setQuantity((int) restockRequest.getQuantity());
            }

            if (restockRequest.getDateExpiry() != null) {
                productBatchEntity.setDateExpiry(Timestamp.valueOf(restockRequest.getDateExpiry()));
            }

            PersonEntity personEntity = entityManager.getReference(PersonEntity.class, restockRequest.getPersonId());
            UserEntity userEntity = entityManager.getReference(UserEntity.class, restockRequest.getUserId());
            OperationTypeEntity operationTypeEntity = entityManager.getReference(OperationTypeEntity.class, (short) 1);
            BoxEntity boxEntity = entityManager.getReference(BoxEntity.class, restockRequest.getBoxId());

            // Save new Sell
            SellEntity sellEntity = new SellEntity();
            sellEntity.setTotal(total);
            sellEntity.setPersonByPersonId(personEntity);
            sellEntity.setOperationTypeByOperationTypeId(operationTypeEntity);
            sellEntity.setUserByUserId(userEntity);
            sellEntity.setBoxByBoxId(boxEntity);
            sellEntity.setStatus(true);

            SellEntity newSellEntity = sellRepository.saveAndFlush(sellEntity);

            // Save new Operation
            operationEntity.setProductByProductId(productEntity.get());
            operationEntity.setOperationTypeByOperationTypeId(operationTypeEntity);
            operationEntity.setSellBySellId(newSellEntity);
            operationEntity.setStatus(true);

            OperationEntity newOperation = operationRepository.saveAndFlush(operationEntity);

            // Save new product batch
            productBatchEntity.setOperationByOperationId(newOperation);
            productBatchEntity.setProductByProductId(productEntity.get());
            productBatchEntity.setPriceIn(productEntity.get().getPriceIn());
            productBatchEntity.setQuantity(restockRequest.getQuantity());

            productBatchRepository.save(productBatchEntity);
        }

        return new RestockResponse("Ok");
    }

    @Override
    public ProductsResponse findProductByBarcodeWithQuantity(String barcode) {
        return productRepository.findBarcodeWithQuantity(barcode).orElse(null);
    }

    @Override
    public SellResponse removeStockByProductId(ProductsSellRequest productsSellRequest) {
        List<ProductBatchEntity> batchEntityList = productBatchRepository.findAllProductBatchByProductIdOrderDateExpiry(productsSellRequest.getProductId());
        for (ProductBatchEntity productBatch : batchEntityList) {
            if (productsSellRequest.getQuantity() > 0) {
                double currentQuantity = productBatch.getQuantity();
                int removeQuantity = productsSellRequest.getQuantity();

                if (currentQuantity >= removeQuantity) {
                    productBatch.setQuantity(currentQuantity - removeQuantity);
                    productsSellRequest.setQuantity(0);
                } else {
                    productBatch.setQuantity(0.0);
                    productsSellRequest.setQuantity((int) (removeQuantity - currentQuantity));
                }

                if (productBatch.getQuantity() == 0) {
                    productBatchRepository.delete(productBatch);
                } else {
                    productBatchRepository.save(productBatch);
                }
            }
        }

        return new SellResponse("Ok");
    }
}
