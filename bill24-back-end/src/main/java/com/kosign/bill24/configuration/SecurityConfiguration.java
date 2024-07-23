package com.kosign.bill24.configuration;

import com.kosign.bill24.configuration.jwtconfiguration.JwtAuthenticationException;
import com.kosign.bill24.configuration.jwtconfiguration.JwtAuthenticationFilter;
import com.kosign.bill24.enums.ErrorCode;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity
public class SecurityConfiguration {

    private final AuthenticationProvider authenticationProvider;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final JwtAuthenticationException jwtAuthenticationException;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorize ->
                        authorize.requestMatchers("/", "/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui-html").permitAll()
                                .requestMatchers("/api/v1/auth/**").permitAll()
//                                .requestMatchers("/api/lms/v1/status/**").hasAnyAuthority()
                                .anyRequest()
                                .authenticated()
                )
                .exceptionHandling(exceptionHandling ->
                        exceptionHandling
                                .accessDeniedHandler(this::accessDeniedHandler)
                                .authenticationEntryPoint(this::unauthorizedHandler)
                )
                .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    private void accessDeniedHandler(HttpServletRequest request, HttpServletResponse response, AccessDeniedException e) {
        jwtAuthenticationException.jwtExceptionHandler(response, ErrorCode.FORBIDDEN);
    }

    public void unauthorizedHandler(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) {
        jwtAuthenticationException.jwtExceptionHandler(response, ErrorCode.UNAUTHORIZED);
    }

}
