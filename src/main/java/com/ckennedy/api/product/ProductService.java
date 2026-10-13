package com.ckennedy.api.product;

import com.ckennedy.api.exception.ProductNotFoundException;
import com.ckennedy.api.generated.model.ProductCreateRequest;
import com.ckennedy.api.generated.model.ProductResponse;
import com.ckennedy.api.generated.model.ProductUpdateRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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

    @Transactional(readOnly = true)
    public ProductResponse findById(
            String id,
            boolean admin
    ) {

        ProductEntity product = productRepository
                .findById(id)
                .orElseThrow(ProductNotFoundException::new);

        if (!Boolean.TRUE.equals(product.getIsPublished())
                && !admin) {

            throw new ProductNotFoundException();
        }

        return productMapper.toResponse(product);
    }

    @Transactional
    public ProductResponse create(
            ProductCreateRequest request
    ) {

        ProductEntity product =
                productMapper.toEntity(request);

        // Sécurisation des valeurs par défaut

        if (product.getSizes() == null) {
            product.setSizes(new ArrayList<>());
        }

        if (product.getColors() == null) {
            product.setColors(new ArrayList<>());
        }

        if (product.getSpecs() == null) {
            product.setSpecs(new ArrayList<>());
        }

        if (product.getSubtitle() == null) {
            product.setSubtitle("");
        }

        if (product.getSeason() == null) {
            product.setSeason("");
        }

        if (product.getBadge() == null) {
            product.setBadge("");
        }

        if (product.getDescription() == null) {
            product.setDescription("");
        }

        if (product.getInStock() == null) {
            product.setInStock(true);
        }

        if (product.getIsPublished() == null) {
            product.setIsPublished(true);
        }

        // Toujours contrôlé par le backend
        product.setLikesCount(0L);

        ProductEntity savedProduct =
                productRepository.save(product);

        return productMapper.toResponse(savedProduct);
    }

    @Transactional
    public ProductResponse update(
            String id,
            ProductUpdateRequest request
    ) {

        ProductEntity product = productRepository
                .findById(id)
                .orElseThrow(ProductNotFoundException::new);

        productMapper.updateEntity(
                request,
                product
        );

        ProductEntity updatedProduct =
                productRepository.saveAndFlush(product);

        return productMapper.toResponse(updatedProduct);
    }
}
