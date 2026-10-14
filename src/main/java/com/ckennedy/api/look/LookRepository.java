package com.ckennedy.api.look;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LookRepository
        extends JpaRepository<LookEntity, String> {

    @Query("""
        select distinct look
        from LookEntity look
        join look.includedProductIds productId
        where productId = :productId
    """)
    List<LookEntity> findAllContainingProduct(
            @Param("productId") String productId
    );

    List<LookEntity> findAllByOrderByNumberAsc();
}
