package com.kosign.bill24.repository;

import com.kosign.bill24.model.entity.UserInfm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserInfmRepository extends JpaRepository<UserInfm, Integer> {

    Optional<UserInfm> findByEml(@Param("EML") String email);

}
