package br.com.douglasffilho.oauthapplication.utils.impl;

import br.com.douglasffilho.oauthapplication.dto.UserDTO;
import br.com.douglasffilho.oauthapplication.entities.User;
import br.com.douglasffilho.oauthapplication.utils.EntityFactory;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class UserFactory implements EntityFactory<User> {

    private UserDTO userDTO;

    private User createValid(final UserDTO userDTO) {

        return User
                .builder()
                .username(userDTO.getName())
                .email(userDTO.getEmail())
                .password(userDTO.getPassword())
                .phone(userDTO.getPhone())
                .isAccountNonExpired(true)
                .isAccountNonLocked(true)
                .isCredentialsNonExpired(true)
                .isEnabled(true)
                .accountExpirationDate(LocalDateTime.now().plusYears(1))
                .credentialsExpirationDate(LocalDateTime.now().plusMonths(3))
                .build();
    }

    private User createValidTest(final UserDTO userDTO) {
        return User
                .builder()
                .username(userDTO.getName())
                .email(userDTO.getEmail())
                .password(userDTO.getPassword())
                .phone(userDTO.getPhone())
                .isAccountNonExpired(true)
                .isAccountNonLocked(true)
                .isCredentialsNonExpired(true)
                .isEnabled(true)
                .accountExpirationDate(LocalDateTime.now().plusYears(1))
                .credentialsExpirationDate(LocalDateTime.now().plusMonths(3))
                .build();
    }

    @Override
    public User createValid() {
        return this.createValid(this.userDTO);
    }

    @Override
    public User createTest() {
        return this.createValidTest(this.userDTO);
    }
}
