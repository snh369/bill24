package com.kosign.bill24.configuration.jwtconfiguration;

import com.kosign.bill24.enums.ErrorCode;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.SignatureException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtAuthenticationService jwtAuthenticationService;
    private final UserDetailsService userDetailsService;
    private final JwtAuthenticationException jwtAuthenticationException;

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {

        if (request.getServletPath().contains("/sign_in")) {
            logger.info("Authorization header is null or does not start with Bearer String");
            filterChain.doFilter(request, response);
            return;
        }

        final String authHeader = request.getHeader("Authorization");
        final String userLogin;
        final String jwt;

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        jwt = authHeader.substring(7);
        try {
            userLogin = jwtAuthenticationService.extractUsername(jwt);

            if (userLogin != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                UserDetails userDetails = this.userDetailsService.loadUserByUsername(userLogin);

                if (jwtAuthenticationService.isTokenValid(jwt, userDetails)) {
                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                            userDetails,
                            null,
                            userDetails.getAuthorities()
                    );
                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }
            }
            filterChain.doFilter(request, response);
        } catch (SignatureException e) {
            jwtAuthenticationException.jwtExceptionHandler(response, ErrorCode.INVALID_TOKEN_SIGNATURE);
        } catch (MalformedJwtException | IllegalArgumentException e) {
            jwtAuthenticationException.jwtExceptionHandler(response, ErrorCode.INVALID_TOKEN);
        } catch (ExpiredJwtException e) {
            jwtAuthenticationException.jwtExceptionHandler(response, ErrorCode.TOKEN_EXPIRED);
        } catch (UnsupportedJwtException e) {
            jwtAuthenticationException.jwtExceptionHandler(response, ErrorCode.UNSUPPORTED_TOKEN);
        }

    }
}
