package com.ckennedy.api.product;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository
        extends JpaRepository<ProductEntity, String> {

    List<ProductEntity>
    findAllByIsPublishedTrueOrderByCreatedAtDesc();

    List<ProductEntity>
    findAllByOrderByCreatedAtDesc();

    List<ProductEntity>
    findByIsPublishedTrueOrderByLikesCountDesc(
            Pageable pageable
    );

    @Modifying
    @Query(
            value = """
                    UPDATE products
                    SET likes_count = likes_count + 1,
                        updated_at = CURRENT_TIMESTAMP
                    WHERE id = :id
                    """,
            nativeQuery = true
    )
    int incrementLikes(
            @Param("id") String id
    );

    @Modifying
    @Query(
            value = """
                    UPDATE products
                    SET likes_count = GREATEST(likes_count - 1, 0),
                        updated_at = CURRENT_TIMESTAMP
                    WHERE id = :id
                    """,
            nativeQuery = true
    )
    int decrementLikes(
            @Param("id") String id
    );
}
