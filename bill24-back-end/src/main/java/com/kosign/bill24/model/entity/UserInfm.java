package com.kosign.bill24.model.entity;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "USER_INFM")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class UserInfm implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USR_ID", nullable = false)
    private int usrId;

    @Column(name = "USR_NM")
    private String usrNm;

    @Column(name = "USR_PW", nullable = false)
    private String usrPw;

    @Column(name = "TEL")
    private String tel;

    @Column(name = "EML", nullable = false, unique = true)
    private String eml;

    @Column(name = "USR_IMG")
    private String usrImg;

    @Column(name = "LOCK_YN")
    private String lockYn;

    @Column(name = "ACT_YN")
    private String actYn;

    @Column(name = "REG_ID")
    private String regId;

    @Column(name = "LST_LGN_DTM")
    private String lstLgnDtm;

    @Column(name = "REG_DT", updatable = false)
    private String regDt;

    @Column(name = "MOD_ID")
    private String modId;

    @Column(name = "MOD_DT")
    private String modDt;

    @Override
    public String getPassword() {
        return usrPw;
    }

    @Override
    public String getUsername() {
        return usrNm;
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
//        for (String role : roles) {
//            authorities.add(new SimpleGrantedAuthority(role));
//        }
        authorities.add(new SimpleGrantedAuthority("USER"));
        return authorities;
    }

    public static UserInfm getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (UserInfm) authentication.getPrincipal();
    }

}
