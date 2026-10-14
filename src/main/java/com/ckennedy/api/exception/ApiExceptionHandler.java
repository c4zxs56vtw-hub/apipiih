package com.ckennedy.api.exception;

import com.ckennedy.api.generated.model.ApiError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(EmailAlreadyUsedException.class)
    public ResponseEntity<ApiError> handleEmailAlreadyUsed(
            EmailAlreadyUsedException exception
    ) {

        ApiError error = new ApiError();
        error.setMessage(exception.getMessage());
        error.setField("email");

        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(error);
    }

    @ExceptionHandler(InvalidCredentialsException.class)
    public ResponseEntity<ApiError> handleInvalidCredentials(
            InvalidCredentialsException exception
    ) {

        ApiError error = new ApiError();
        error.setMessage(exception.getMessage());

        return ResponseEntity
                .badRequest()
                .body(error);
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<ApiError> handleUnauthorized(
            UnauthorizedException exception
    ) {

        ApiError error = new ApiError();
        error.setMessage(exception.getMessage());

        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(error);
    }

    @ExceptionHandler(ForbiddenException.class)
    public ResponseEntity<ApiError> handleForbidden(
            ForbiddenException exception
    ) {

        ApiError error = new ApiError();
        error.setMessage(exception.getMessage());

        return ResponseEntity
                .status(HttpStatus.FORBIDDEN)
                .body(error);
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ApiError> handleProductNotFound(
            ProductNotFoundException exception
    ) {

        ApiError error = new ApiError();
        error.setMessage(exception.getMessage());

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(error);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApiError> handleUnreadableBody(
            HttpMessageNotReadableException exception
    ) {

        ApiError error = new ApiError();

        error.setMessage(
                "Le corps de la requête est invalide."
        );

        return ResponseEntity
                .badRequest()
                .body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> handleValidation(
            MethodArgumentNotValidException exception
    ) {

        var fieldError = exception
                .getBindingResult()
                .getFieldErrors()
                .get(0);

        ApiError error = new ApiError();

        error.setMessage(
                fieldError.getDefaultMessage()
        );

        error.setField(
                fieldError.getField()
        );

        return ResponseEntity
                .badRequest()
                .body(error);
    }

    @ExceptionHandler(InvalidLimitException.class)
    public ResponseEntity<ApiError> handleInvalidLimit(
            InvalidLimitException exception
    ) {

        ApiError error = new ApiError();

        error.setMessage(exception.getMessage());
        error.setField("limit");

        return ResponseEntity
                .badRequest()
                .body(error);
    }

    @ExceptionHandler(InvalidLookProductsException.class)
    public ResponseEntity<ApiError> handleInvalidLookProducts(
            InvalidLookProductsException exception
    ) {

        ApiError error = new ApiError();

        error.setMessage(exception.getMessage());
        error.setField("includedProductIds");

        return ResponseEntity
                .badRequest()
                .body(error);
    }

    @ExceptionHandler(LookNotFoundException.class)
    public ResponseEntity<ApiError> handleLookNotFound(
            LookNotFoundException exception
    ) {

        ApiError error = new ApiError();
        error.setMessage(exception.getMessage());

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(error);
    }

    @ExceptionHandler(InvalidUploadException.class)
    public ResponseEntity<ApiError> handleInvalidUpload(
            InvalidUploadException exception
    ) {

        ApiError error = new ApiError();
        error.setMessage(exception.getMessage());

        return ResponseEntity
                .badRequest()
                .body(error);
    }

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ResponseEntity<ApiError> handleFileTooLarge(
            MaxUploadSizeExceededException exception
    ) {

        ApiError error = new ApiError();

        error.setMessage(
                "Le fichier dépasse la taille maximale de 5 Mo."
        );

        return ResponseEntity
                .status(HttpStatus.PAYLOAD_TOO_LARGE)
                .body(error);
    }
}
