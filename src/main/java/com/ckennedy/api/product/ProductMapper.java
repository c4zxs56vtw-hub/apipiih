package com.ckennedy.api.product;

import com.ckennedy.api.generated.model.ProductColor;
import com.ckennedy.api.generated.model.ProductImages;
import com.ckennedy.api.generated.model.ProductResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.math.BigDecimal;
import java.net.URI;
import java.util.List;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface ProductMapper {

    @Mapping(target = "priceFcfa", source = "price")
    ProductResponse toResponse(ProductEntity entity);

    List<ProductResponse> toResponseList(
            List<ProductEntity> entities
    );

    ProductImages toResponse(
            ProductImagesEntity images
    );

    ProductColor toResponse(
            ProductColorEntity color
    );

    default URI map(String value) {

        if (value == null || value.isBlank()) {
            return null;
        }

        return URI.create(value);
    }

    default BigDecimal map(Double value) {
        if (value == null) {
            return null;
        }
        return BigDecimal.valueOf(value);
    }

    default ProductResponse.CategoryEnum mapCategory(
            String category
    ) {

        if (category == null) {
            return null;
        }

        return ProductResponse.CategoryEnum
                .fromValue(category);
    }
}
