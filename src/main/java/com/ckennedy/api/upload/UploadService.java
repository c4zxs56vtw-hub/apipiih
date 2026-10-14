package com.ckennedy.api.upload;

import com.ckennedy.api.exception.InvalidUploadException;
import com.ckennedy.api.generated.model.UploadResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Locale;
import java.util.Set;
import java.util.UUID;

@Service
public class UploadService {

    private static final long MAX_FILE_SIZE =
            5L * 1024 * 1024;

    private static final Set<String> ALLOWED_EXTENSIONS =
            Set.of("jpg", "jpeg", "png", "webp");

    private static final Set<String> ALLOWED_MIME_TYPES =
            Set.of(
                    "image/jpeg",
                    "image/png",
                    "image/webp"
            );

    private final Path uploadRoot;

    public UploadService(
            @Value("${app.upload.directory}") String directory
    ) {

        this.uploadRoot = Paths
                .get(directory)
                .toAbsolutePath()
                .normalize();
    }

    public UploadResponse upload(
            MultipartFile file,
            String targetValue
    ) {

        validateBasicFile(file);

        UploadTarget target;

        try {
            target = UploadTarget.fromValue(targetValue);
        } catch (IllegalArgumentException exception) {
            throw new InvalidUploadException(
                    "Target d'upload invalide."
            );
        }

        String extension =
                getExtension(file.getOriginalFilename());

        validateExtension(extension);
        validateMimeType(file.getContentType());
        validateSignature(file, extension);

        String filename =
                UUID.randomUUID()
                        + "."
                        + normalizeExtension(extension);

        Path targetDirectory =
                uploadRoot.resolve(
                        target.getValue()
                );

        Path destination =
                targetDirectory
                        .resolve(filename)
                        .normalize();

        /*
         * Protection supplémentaire contre une éventuelle
         * sortie du dossier d'upload.
         */
        if (!destination.startsWith(targetDirectory)) {
            throw new InvalidUploadException(
                    "Nom de fichier invalide."
            );
        }

        try {

            Files.createDirectories(targetDirectory);

            Files.copy(
                    file.getInputStream(),
                    destination,
                    StandardCopyOption.REPLACE_EXISTING
            );

        } catch (IOException exception) {

            throw new InvalidUploadException(
                    "Impossible d'enregistrer l'image."
            );
        }

        String url =
                ServletUriComponentsBuilder
                        .fromCurrentContextPath()
                        .path("/uploads/")
                        .path(target.getValue())
                        .path("/")
                        .path(filename)
                        .toUriString();

        UploadResponse response =
                new UploadResponse();

        response.setUrl(
                java.net.URI.create(url)
        );

        response.setFilename(filename);
        response.setSize(file.getSize());

        return response;
    }

    private void validateBasicFile(
            MultipartFile file
    ) {

        if (file == null || file.isEmpty()) {
            throw new InvalidUploadException(
                    "Le fichier est obligatoire."
            );
        }

        if (file.getSize() > MAX_FILE_SIZE) {
            throw new InvalidUploadException(
                    "Le fichier dépasse la taille maximale de 5 Mo."
            );
        }
    }

    private String getExtension(
            String filename
    ) {

        if (filename == null) {
            throw new InvalidUploadException(
                    "Le fichier doit avoir une extension."
            );
        }

        int dotIndex =
                filename.lastIndexOf('.');

        if (dotIndex < 0 ||
                dotIndex == filename.length() - 1) {

            throw new InvalidUploadException(
                    "Le fichier doit avoir une extension valide."
            );
        }

        return filename
                .substring(dotIndex + 1)
                .toLowerCase(Locale.ROOT);
    }

    private void validateExtension(
            String extension
    ) {

        if (!ALLOWED_EXTENSIONS.contains(extension)) {
            throw new InvalidUploadException(
                    "Seules les images JPEG, PNG et WebP sont autorisées."
            );
        }
    }

    private void validateMimeType(
            String mimeType
    ) {

        if (mimeType == null ||
                !ALLOWED_MIME_TYPES.contains(mimeType)) {

            throw new InvalidUploadException(
                    "Type MIME de l'image invalide."
            );
        }
    }

    private void validateSignature(
            MultipartFile file,
            String extension
    ) {

        byte[] header;

        try {

            byte[] bytes = file.getBytes();

            if (bytes.length < 12) {
                throw new InvalidUploadException(
                        "Le contenu du fichier image est invalide."
                );
            }

            header = bytes;

        } catch (IOException exception) {

            throw new InvalidUploadException(
                    "Impossible de lire le fichier."
            );
        }

        boolean valid;

        switch (normalizeExtension(extension)) {

            case "jpg" ->
                    valid = isJpeg(header);

            case "png" ->
                    valid = isPng(header);

            case "webp" ->
                    valid = isWebp(header);

            default ->
                    valid = false;
        }

        if (!valid) {
            throw new InvalidUploadException(
                    "La signature réelle du fichier ne correspond pas à son format."
            );
        }
    }

    private boolean isJpeg(
            byte[] bytes
    ) {

        return bytes.length >= 3
                && (bytes[0] & 0xFF) == 0xFF
                && (bytes[1] & 0xFF) == 0xD8
                && (bytes[2] & 0xFF) == 0xFF;
    }

    private boolean isPng(
            byte[] bytes
    ) {

        int[] signature = {
                0x89, 0x50, 0x4E, 0x47,
                0x0D, 0x0A, 0x1A, 0x0A
        };

        for (int i = 0; i < signature.length; i++) {

            if ((bytes[i] & 0xFF) != signature[i]) {
                return false;
            }
        }

        return true;
    }

    private boolean isWebp(
            byte[] bytes
    ) {

        return bytes.length >= 12

                && bytes[0] == 'R'
                && bytes[1] == 'I'
                && bytes[2] == 'F'
                && bytes[3] == 'F'

                && bytes[8] == 'W'
                && bytes[9] == 'E'
                && bytes[10] == 'B'
                && bytes[11] == 'P';
    }

    private String normalizeExtension(
            String extension
    ) {

        if ("jpeg".equals(extension)) {
            return "jpg";
        }

        return extension;
    }
}
