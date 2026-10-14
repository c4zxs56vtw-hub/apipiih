package com.ckennedy.api.look;

import com.ckennedy.api.generated.model.LookRequest;
import com.ckennedy.api.generated.model.LookResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/looks")
public class LookController {

    private final LookService lookService;

    public LookController(LookService lookService) {
        this.lookService = lookService;
    }

    @GetMapping
    public ResponseEntity<List<LookResponse>> findAll() {
        return ResponseEntity.ok(
                lookService.findAll()
        );
    }

    @PostMapping
    public ResponseEntity<LookResponse> create(
            @Valid
            @RequestBody LookRequest request
    ) {

        LookResponse response =
                lookService.create(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LookResponse> findById(
            @PathVariable String id
    ) {

        return ResponseEntity.ok(
            lookService.findById(id)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<LookResponse> update(
            @PathVariable String id,

            @Valid
            @RequestBody LookRequest request
    ) {

        return ResponseEntity.ok(
                lookService.update(id, request)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable String id
    ) {

        lookService.delete(id);

        return ResponseEntity
                .noContent()
                .build();
    }
}
