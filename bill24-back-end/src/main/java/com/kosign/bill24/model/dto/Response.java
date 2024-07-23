package com.kosign.bill24.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Response<T> {

    @JsonProperty("timestamp")
    LocalDateTime timestamp;

    @JsonProperty("type")
    private HttpStatus type;

    @JsonProperty("status")
    private Integer status;

    @JsonProperty("message")
    private String message;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    T payload;

    public static <T> ResponseEntity<Response<T>> create(HttpStatus status, String message, T payload) {
        return ResponseEntity.status(status).body(
                Response.<T>builder()
                        .timestamp(LocalDateTime.now())
                        .type(status)
                        .status(status.value())
                        .message(message)
                        .payload(payload)
                        .build()
        );
    }

    public static  ResponseEntity<Object> create(HttpStatus status, String message) {
        return ResponseEntity.status(status).body(
                Response.builder()
                        .timestamp(LocalDateTime.now())
                        .type(status)
                        .status(status.value())
                        .message(message)
                        .build()
        );
    }

}
