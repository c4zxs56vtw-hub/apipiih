package com.ckennedy.api.look;

import com.ckennedy.api.generated.model.LookRequest;
import com.ckennedy.api.generated.model.LookResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

import java.net.URI;
import java.util.List;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface LookMapper {

    // Entity -> Response
    LookResponse toResponse(LookEntity entity);

    List<LookResponse> toResponseList(
            List<LookEntity> entities
    );

    // Request -> Entity
    @Mapping(target = "id", ignore = true)
    LookEntity toEntity(LookRequest request);

    @Mapping(target = "id", ignore = true)
    void updateEntity(
            LookRequest request,
            @MappingTarget LookEntity entity
    );

    default URI stringToUri(String value) {

        if (value == null || value.isBlank()) {
            return null;
        }

        return URI.create(value);
    }

    default String uriToString(URI value) {

        if (value == null) {
            return null;
        }

        return value.toString();
    }
}
