package com.ckennedy.api.product;

import com.ckennedy.api.exception.ForbiddenException;
import com.ckennedy.api.exception.UnauthorizedException;
import com.ckennedy.api.generated.model.ProductResponse;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<ProductResponse>>
    findAll(
            @RequestParam(
                    defaultValue = "false"
            ) boolean admin,

            Authentication authentication
    ) {

        boolean isAdmin =
                authentication != null &&
                authentication
                        .getAuthorities()
                        .stream()
                        .anyMatch(authority ->
                                authority
                                        .getAuthority()
                                        .equals("ROLE_ADMIN")
                        );

        if (admin) {

            if (authentication == null) {
                throw new UnauthorizedException();
            }

            if (!isAdmin) {
                throw new ForbiddenException();
            }
        }

        return ResponseEntity.ok(
                productService.findAll(admin)
        );
    }
}
