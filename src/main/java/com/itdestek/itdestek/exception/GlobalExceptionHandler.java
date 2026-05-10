package com.itdestek.itdestek.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice // Tüm controller sınıflarındaki hataları dinleyen merkezî gözlemci
public class GlobalExceptionHandler {

    // Eğer kullanıcı (Postman) validasyon kurallarına uymazsa (Örn: Boş başlık gönderirse) bu metot çalışır
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidationErrors(MethodArgumentNotValidException exception) {

        // 1. Hangi alanda (field) hangi hata var? Onları bir listeye (map) topluyoruz.
        Map<String, String> validationErrors = new HashMap<>();

        exception.getBindingResult().getFieldErrors().forEach(error -> {
            validationErrors.put(error.getField(), error.getDefaultMessage());
        });

        // 2. Postman'de görünecek olan şık hata paketini hazırlıyoruz.
        Map<String, Object> responseBody = new HashMap<>();
        responseBody.put("timestamp", LocalDateTime.now()); // Hatanın olduğu an
        responseBody.put("status", HttpStatus.BAD_REQUEST.value()); // 400 hatası
        responseBody.put("error", "Doğrulama Hatası!"); // Hatanın adı
        responseBody.put("messages", validationErrors); // Detaylı mesajlar

        return ResponseEntity.badRequest().body(responseBody);
    }
}
