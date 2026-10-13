package com.ckennedy.api.product;

import com.ckennedy.api.exception.ForbiddenException;
import com.ckennedy.api.exception.UnauthorizedException;
import com.ckennedy.api.generated.model.ProductCreateRequest;
import com.ckennedy.api.generated.model.ProductResponse;
import com.ckennedy.api.generated.model.ProductUpdateRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(
            ProductService productService
    ) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<List<ProductResponse>> findAll(
            @RequestParam(
                    defaultValue = "false"
            ) boolean admin,

            Authentication authentication
    ) {

        if (!admin) {
            return ResponseEntity.ok(
                    productService.findAll(false)
            );
        }

        boolean authenticated =
                authentication != null
                && authentication.isAuthenticated()
                && !(authentication
                instanceof AnonymousAuthenticationToken);

        if (!authenticated) {
            throw new UnauthorizedException();
        }

        boolean isAdmin =
                authentication
                        .getAuthorities()
                        .stream()
                        .anyMatch(authority ->
                                authority
                                        .getAuthority()
                                        .equals("ROLE_ADMIN")
                        );

        if (!isAdmin) {
            throw new ForbiddenException();
        }

        return ResponseEntity.ok(
                productService.findAll(true)
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> findById(
            @PathVariable String id,
            Authentication authentication
    ) {

        boolean isAdmin =
                authentication != null
                && authentication.isAuthenticated()
                && !(authentication
                        instanceof AnonymousAuthenticationToken)
                && authentication
                        .getAuthorities()
                        .stream()
                        .anyMatch(authority ->
                                authority
                                        .getAuthority()
                                        .equals("ROLE_ADMIN")
                        );

        ProductResponse product =
                productService.findById(id, isAdmin);

        return ResponseEntity.ok(product);
    }

    @PostMapping
    public ResponseEntity<ProductResponse> create(
            @Valid
            @RequestBody ProductCreateRequest request
    ) {

        ProductResponse product =
                productService.create(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(product);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponse> update(
            @PathVariable String id,

            @Valid
            @RequestBody ProductUpdateRequest request
    ) {

        ProductResponse product =
                productService.update(id, request);

        return ResponseEntity.ok(product);
    }
}
