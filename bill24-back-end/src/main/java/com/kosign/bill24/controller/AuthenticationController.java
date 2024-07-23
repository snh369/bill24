package com.kosign.bill24.controller;

import com.kosign.bill24.model.dto.authentication.request.SignInRequest;
import com.kosign.bill24.model.dto.authentication.request.SignUpRequest;
import com.kosign.bill24.model.dto.authentication.response.SignInResponse;
import com.kosign.bill24.model.dto.authentication.response.SignUpResponse;
import com.kosign.bill24.service.authentication.AuthenticationService;
import com.kosign.bill24.model.dto.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
@Tag(name = "Authentication")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/sign_in")
    @Operation(summary = "Log into the account")
    public ResponseEntity<Response<SignInResponse>> sign_in(@Valid @RequestBody SignInRequest signInRequest) {
        return authenticationService.sign_in(signInRequest);
    }

    @PostMapping("/sign_up")
    @Operation(summary = "Create a new account")
    public ResponseEntity<Response<SignUpResponse>> sign_up(@Valid @RequestBody SignUpRequest signUpUser) {
        return authenticationService.sign_up(signUpUser);
    }

    @PostMapping("/sign_out")
    @Operation(summary = "Log out of account")
    public ResponseEntity<?> sign_out() {
        return authenticationService.sign_out();
    }
}
