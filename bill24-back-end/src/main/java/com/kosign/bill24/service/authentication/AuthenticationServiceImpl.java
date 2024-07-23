package com.kosign.bill24.service.authentication;

import com.kosign.bill24.configuration.jwtconfiguration.JwtAuthenticationService;
import com.kosign.bill24.exception.BadRequestExceptionClass;
import com.kosign.bill24.exception.NotFoundExceptionClass;
import com.kosign.bill24.model.dto.authentication.request.SignInRequest;
import com.kosign.bill24.model.dto.authentication.request.SignUpRequest;
import com.kosign.bill24.model.dto.authentication.response.SignInResponse;
import com.kosign.bill24.model.dto.authentication.response.SignUpResponse;
import com.kosign.bill24.model.entity.UserInfm;
import com.kosign.bill24.repository.UserInfmRepository;
import com.kosign.bill24.service.userinfm.UserInfmService;
import com.kosign.bill24.utilities.Message;
import com.kosign.bill24.model.dto.Response;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final JwtAuthenticationService jwtAuthenticationService;
    private final AuthenticationManager authenticationManager;
    private final UserInfmService userInfmService;
    private final UserInfmRepository userInfmRepository;
    private final PasswordEncoder passwordEncoder;
    private static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationService.class);

    @Override
    public ResponseEntity<Response<SignInResponse>> sign_in(SignInRequest signInRequest) {

        UserInfm userEntity = userInfmService.findByEml(signInRequest.getEmail())
                .orElseThrow(() -> new NotFoundExceptionClass("User " + signInRequest.getEmail() + " not found"));

        if (!passwordEncoder.matches(signInRequest.getPassword(), userEntity.getPassword())) {
            throw new BadRequestExceptionClass("PASSWORD INCORRECT RESPONSE");
        }

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(signInRequest.getEmail(), signInRequest.getPassword())
        );

        final String accessToken = jwtAuthenticationService.generateToken(userEntity);
        SignInResponse signInResponse = SignInResponse.builder()
                .userId(userEntity.getUsrId())
                .userName(userEntity.getUsername())
                .email(userEntity.getEml())
                .accessToken(accessToken)
                .build();

        return Response.create(HttpStatus.OK, Message.loginSuccess(), signInResponse);
    }

    @Override
    public ResponseEntity<Response<SignUpResponse>> sign_up(SignUpRequest signUpRequest) {
        Optional<UserInfm> userEntity = userInfmService.findByEml(signUpRequest.getEmail());
        if (userEntity.isPresent()) throw new BadRequestExceptionClass("User " + signUpRequest.getEmail() + " already exists");

        UserInfm newUserInfm = UserInfm.builder()
                .usrNm(signUpRequest.getUserName())
                .eml(signUpRequest.getEmail())
                .usrPw(passwordEncoder.encode(signUpRequest.getPassword()))
                .build();
        userInfmRepository.save(newUserInfm);

        final String accessToken = jwtAuthenticationService.generateToken(newUserInfm);
        SignUpResponse signUpResponse = SignUpResponse.builder()
                .userId(newUserInfm.getUsrId())
                .userName(newUserInfm.getUsername())
                .email(newUserInfm.getEml())
                .accessToken(accessToken)
                
                .build();
        return Response.create(HttpStatus.OK, Message.loginSuccess(), signUpResponse);
    }

    @Override
    public ResponseEntity<?> sign_out() {
        return Response.create(HttpStatus.OK, "Sign Out");
    }
}
