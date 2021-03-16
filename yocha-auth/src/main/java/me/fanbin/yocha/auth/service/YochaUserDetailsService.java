package me.fanbin.yocha.auth.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * @author fanbin
 * @date 2021/3/8
 */
@Service
@RequiredArgsConstructor
public class YochaUserDetailsService implements UserDetailsService {

    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // TODO: 临时逻辑
        return new User(username, passwordEncoder.encode("123456"),
                AuthorityUtils.createAuthorityList("admin"));
    }
}
