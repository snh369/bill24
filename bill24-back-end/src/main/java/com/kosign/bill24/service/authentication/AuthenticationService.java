package com.kosign.bill24.service.authentication;

import com.kosign.bill24.model.dto.authentication.request.SignInRequest;
import com.kosign.bill24.model.dto.authentication.request.SignUpRequest;
import com.kosign.bill24.model.dto.authentication.response.SignInResponse;
import com.kosign.bill24.model.dto.authentication.response.SignUpResponse;
import com.kosign.bill24.model.dto.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface AuthenticationService {

    ResponseEntity<Response<SignInResponse>> sign_in(SignInRequest signInRequest);

    ResponseEntity<Response<SignUpResponse>> sign_up(SignUpRequest signUpRequest);

    ResponseEntity<?> sign_out();
}
