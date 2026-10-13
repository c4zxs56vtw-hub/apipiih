package com.ckennedy.api.product;

import com.ckennedy.api.generated.model.ProductResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public ProductService(
            ProductRepository productRepository,
            ProductMapper productMapper
    ) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }


    @Transactional(readOnly = true)
    public List<ProductResponse> findAll(
            boolean includeDrafts
    ) {

        List<ProductEntity> products;

        if (includeDrafts) {
            products =
                    productRepository
                            .findAllByOrderByCreatedAtDesc();
        } else {
            products =
                    productRepository
                            .findAllByIsPublishedTrueOrderByCreatedAtDesc();
        }

        return productMapper
                .toResponseList(products);
    }
}
