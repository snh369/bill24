package com.kosign.bill24.service.userinfm;

import com.kosign.bill24.model.entity.UserInfm;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface UserInfmService {
    Optional<UserInfm> findByEml(@Param("EML") String email);
}
