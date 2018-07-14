package br.com.douglasffilho.oauthapplication.security.utils;

import br.com.douglasffilho.oauthapplication.entities.Role;
import br.com.douglasffilho.oauthapplication.entities.User;
import br.com.douglasffilho.oauthapplication.security.models.JwtUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.stream.Collectors;

public class JwtUserFactory {

    private JwtUserFactory() {

    }

    public static JwtUser create(final User user) {
        return JwtUser
                .builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .password(PasswordUtils.generateBCrypt(user.getPassword()))
                .isAccountNonExpired(true)
                .isAccountNonLocked(true)
                .isCredentialsNonExpired(true)
                .isEnabled(true)
                .authorities(mapToGrantedAuthorities(user.getRoles()))
                .build();
    }

    private static List<GrantedAuthority> mapToGrantedAuthorities(final List<Role> roles) {
        return roles
                .stream()
                .map(role -> new SimpleGrantedAuthority(role.toString()))
                .collect(Collectors.toList());
    }

}
