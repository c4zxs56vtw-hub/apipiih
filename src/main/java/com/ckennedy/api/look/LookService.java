package com.ckennedy.api.look;

import com.ckennedy.api.exception.InvalidLookProductsException;
import com.ckennedy.api.exception.LookNotFoundException;
import com.ckennedy.api.generated.model.LookRequest;
import com.ckennedy.api.generated.model.LookResponse;
import com.ckennedy.api.product.ProductEntity;
import com.ckennedy.api.product.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
public class LookService {

    private final LookRepository lookRepository;
    private final LookMapper lookMapper;
    private final ProductRepository productRepository;

    public LookService(
            LookRepository lookRepository,
            LookMapper lookMapper,
            ProductRepository productRepository
    ) {
        this.lookRepository = lookRepository;
        this.lookMapper = lookMapper;
        this.productRepository = productRepository;
    }

    @Transactional(readOnly = true)
    public List<LookResponse> findAll() {

        List<LookEntity> looks =
                lookRepository.findAllByOrderByNumberAsc();

        return lookMapper.toResponseList(looks);
    }

    @Transactional(readOnly = true)
    public LookResponse findById(String id) {

        LookEntity look = lookRepository
                .findById(id)
                .orElseThrow(LookNotFoundException::new);

        return lookMapper.toResponse(look);
    }

    @Transactional
    public LookResponse create(
            LookRequest request
    ) {

        validateProductIds(
                request.getIncludedProductIds()
        );

        LookEntity look =
                lookMapper.toEntity(request);

        // ID généré uniquement par le backend
        look.setId(
                UUID.randomUUID().toString()
        );

        if (look.getIncludedProductIds() == null) {
            look.setIncludedProductIds(
                    new ArrayList<>()
            );
        }

        LookEntity savedLook =
                lookRepository.save(look);

        return lookMapper.toResponse(savedLook);
    }

    @Transactional
    public LookResponse update(
            String id,
            LookRequest request
    ) {

        LookEntity look = lookRepository
                .findById(id)
                .orElseThrow(LookNotFoundException::new);

        validateProductIds(
                request.getIncludedProductIds()
        );

        lookMapper.updateEntity(
                request,
                look
        );

        if (look.getIncludedProductIds() == null) {
            look.setIncludedProductIds(
                    new ArrayList<>()
            );
        }

        LookEntity updatedLook =
                lookRepository.save(look);

        return lookMapper.toResponse(updatedLook);
    }

    @Transactional
    public void delete(String id) {

        LookEntity look = lookRepository
                .findById(id)
                .orElseThrow(LookNotFoundException::new);

        lookRepository.delete(look);
    }

    private void validateProductIds(
            List<String> productIds
    ) {

        if (productIds == null || productIds.isEmpty()) {
            return;
        }

        Set<String> requestedIds =
                new HashSet<>(productIds);

        Set<String> existingIds =
                new HashSet<>();

        productRepository
                .findAllById(requestedIds)
                .stream()
                .map(ProductEntity::getId)
                .forEach(existingIds::add);

        if (!existingIds.containsAll(requestedIds)) {
            throw new InvalidLookProductsException();
        }
    }
}
