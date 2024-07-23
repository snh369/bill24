package com.kosign.bill24.service.userinfm;

import com.kosign.bill24.model.entity.UserInfm;
import com.kosign.bill24.repository.UserInfmRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserInfmServiceImpl implements UserInfmService{

    private final UserInfmRepository userInfmRepository;

    @Override
    public Optional<UserInfm> findByEml(String email) {
        return userInfmRepository.findByEml(email);
    }
}
