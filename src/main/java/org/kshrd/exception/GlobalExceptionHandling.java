package org.kshrd.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandling {

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ProblemDetail> handleBadRequestException(BadRequestException e) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, e.getMessage());
        problemDetail.setType(URI.create("about:blank"));
        problemDetail.setTitle("Bad Request");
        problemDetail.setStatus(400);
        problemDetail.setInstance(URI.create("/api/v1/venues"));

        Map<String, String> errors = new HashMap<>();
        errors.put("venueName", "must not be blank");
        errors.put("location", "must not be blank");

        problemDetail.setProperty("timestamp", LocalDateTime.now());

        return new ResponseEntity<>(problemDetail, HttpStatus.BAD_REQUEST);
    }

}
