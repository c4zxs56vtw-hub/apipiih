package com.ckennedy.api.upload;

import com.ckennedy.api.generated.model.UploadResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/upload")
public class UploadController {

    private final UploadService uploadService;

    public UploadController(
            UploadService uploadService
    ) {
        this.uploadService = uploadService;
    }

    @PostMapping(
            consumes = "multipart/form-data"
    )
    public ResponseEntity<UploadResponse> upload(
            @RequestPart("file") MultipartFile file,
            @RequestParam("target") String target
    ) {

        return ResponseEntity.ok(
                uploadService.upload(
                        file,
                        target
                )
        );
    }
}
