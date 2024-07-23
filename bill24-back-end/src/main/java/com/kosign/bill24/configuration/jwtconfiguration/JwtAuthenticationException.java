package com.kosign.bill24.configuration.jwtconfiguration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kosign.bill24.enums.ErrorCode;
import com.kosign.bill24.model.dto.Response;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationException {

    private static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationService.class);
    private final ObjectMapper objectMapper;

    public void jwtExceptionHandler(HttpServletResponse response, ErrorCode errorCode) {
        try (ServletServerHttpResponse servletServerHttpResponse = new ServletServerHttpResponse(response)) {

            Response<Object> body = Response.builder()
                    .timestamp(LocalDateTime.now())
                    .type(errorCode.getStatus())
                    .status(errorCode.getStatus().value())
                    .message(errorCode.getMessage())
                    .build();

            servletServerHttpResponse.getServletResponse().setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
            servletServerHttpResponse.getServletResponse().setStatus(errorCode.getStatus().value());
            servletServerHttpResponse.getBody().write(objectMapper.writeValueAsString(body).getBytes());
        } catch (Exception e) {
            logger.error("jwtExceptionHandler {}", e.getMessage());
        }
    }
}
