package com.ckennedy.api.product;

import com.ckennedy.api.generated.model.ProductColor;
import com.ckennedy.api.generated.model.ProductCreateRequest;
import com.ckennedy.api.generated.model.ProductImages;
import com.ckennedy.api.generated.model.ProductResponse;
import com.ckennedy.api.generated.model.ProductUpdateRequest;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

import java.math.BigDecimal;
import java.net.URI;
import java.util.List;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface ProductMapper {

    // ---------- ENTITY -> RESPONSE ----------

    ProductResponse toResponse(ProductEntity product);

    List<ProductResponse> toResponseList(
            List<ProductEntity> products
    );

    ProductImages toResponse(ProductImagesEntity images);

    ProductColor toResponse(ProductColorEntity color);


    // ---------- CREATE REQUEST -> ENTITY ----------

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "likesCount", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    ProductEntity toEntity(ProductCreateRequest request);

    ProductImagesEntity toEntity(ProductImages images);

    ProductColorEntity toEntity(ProductColor color);


    // ---------- UPDATE REQUEST -> ENTITY ----------

    @BeanMapping(
            nullValuePropertyMappingStrategy =
                    NullValuePropertyMappingStrategy.IGNORE
    )
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "likesCount", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    void updateEntity(
            ProductUpdateRequest request,
            @MappingTarget ProductEntity product
    );


    // URI OpenAPI -> String Entity

    default String uriToString(URI uri) {
        return uri == null ? null : uri.toString();
    }


    // String Entity -> URI OpenAPI

    default URI stringToUri(String value) {

        if (value == null || value.isBlank()) {
            return null;
        }

        return URI.create(value);
    }


    // category du Request -> String en base

    default String categoryToString(
            ProductCreateRequest.CategoryEnum category
    ) {

        if (category == null) {
            return null;
        }

        return category.getValue();
    }

    default String updateCategoryToString(
            ProductUpdateRequest.CategoryEnum category
    ) {

        if (category == null) {
            return null;
        }

        return category.getValue();
    }


    // String en base -> enum du Response

    default ProductResponse.CategoryEnum stringToCategory(
            String category
    ) {

        if (category == null) {
            return null;
        }

        return ProductResponse.CategoryEnum.fromValue(category);
    }

    default Double bigDecimalToDouble(BigDecimal value) {
        return value == null ? null : value.doubleValue();
    }

    default BigDecimal doubleToBigDecimal(Double value) {
        return value == null ? null : BigDecimal.valueOf(value);
    }
}
