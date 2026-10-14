package com.ckennedy.api.product;

import com.ckennedy.api.exception.InvalidLimitException;
import com.ckennedy.api.exception.ProductNotFoundException;
import com.ckennedy.api.generated.model.ProductCreateRequest;
import com.ckennedy.api.generated.model.ProductResponse;
import com.ckennedy.api.generated.model.ProductUpdateRequest;
import com.ckennedy.api.look.LookEntity;
import com.ckennedy.api.look.LookRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final LookRepository lookRepository;

    public ProductService(
            ProductRepository productRepository,
            ProductMapper productMapper,
            LookRepository lookRepository
    ) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
        this.lookRepository = lookRepository;
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

    @Transactional
    public void delete(String id) {

        ProductEntity product = productRepository
                .findById(id)
                .orElseThrow(ProductNotFoundException::new);

        List<LookEntity> looks =
                lookRepository.findAllContainingProduct(id);

        for (LookEntity look : looks) {

            look.getIncludedProductIds()
                    .removeIf(productId ->
                            productId.equals(id)
                    );
        }

        productRepository.delete(product);
    }

    @Transactional
    public void like(String id) {

        int updatedRows =
                productRepository.incrementLikes(id);

        if (updatedRows == 0) {
            throw new ProductNotFoundException();
        }
    }

    @Transactional
    public void unlike(String id) {

        int updatedRows =
                productRepository.decrementLikes(id);

        if (updatedRows == 0) {
            throw new ProductNotFoundException();
        }
    }

    @Transactional(readOnly = true)
    public List<ProductResponse> findTrending(
            int limit
    ) {

        if (limit < 1) {
            throw new InvalidLimitException();
        }

        var pageable =
                PageRequest.of(0, limit);

        List<ProductEntity> products =
                productRepository
                        .findByIsPublishedTrueOrderByLikesCountDesc(
                                pageable
                        );

        return productMapper
                .toResponseList(products);
    }
}
