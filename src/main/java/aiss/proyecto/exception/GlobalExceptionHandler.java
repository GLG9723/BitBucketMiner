package aiss.proyecto.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(PageLimitException.class)
    public ResponseEntity<Map<String, Object>> handlePageLimitException(PageLimitException ex) {
        Map<String, Object> body = new HashMap<>();
        body.put("status", HttpStatus.BAD_REQUEST.value());
        body.put("meaning", "Bad Request");
        body.put("error", "Page limit exceeded");
        body.put("message", ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }

    @ExceptionHandler(NegativeParameterException.class)
    public ResponseEntity<Map<String, Object>> handlePageLimitException(NegativeParameterException ex) {
        Map<String, Object> body = new HashMap<>();
        body.put("status", HttpStatus.BAD_REQUEST.value());
        body.put("meaning", "Bad Request");
        body.put("error", "Negative parameter not accepted");
        body.put("message", "The parameters maxPages, nCommits and nIssues must be greater than 0");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }
}
