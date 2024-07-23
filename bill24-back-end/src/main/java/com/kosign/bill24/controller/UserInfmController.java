package com.kosign.bill24.controller;

import com.kosign.bill24.service.userinfm.UserInfmService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/v1/user")
@Tag(name = "User Information")
public class UserInfmController {

    private final UserInfmService userInfmService;

    public UserInfmController(UserInfmService userInfmService) {
        this.userInfmService = userInfmService;
    }

    /**
     * Root GET endpoint.
     *
     * <p>Azure application service has a hidden feature of making requests to root endpoint when
     * "Always On" is turned on.
     * This is the endpoint to deal with that and therefore silence the unnecessary 404s as a response code.
     *
     * @return Welcome message from the service.
     */
    @GetMapping("/")
    public ResponseEntity<?> welcome() {
        return ResponseEntity.ok("Welcome to spring-boot-template");
    }
}
