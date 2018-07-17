package br.com.douglasffilho.oauthapplication.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false, exclude = {"id", "accountExpirationDate", "credentialsExpirationDate"})
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "phone")
    private String phone;

    @Column(name = "account_expiration_date")
    private LocalDateTime accountExpirationDate;

    @Column(name = "credentials_expiration_date")
    private LocalDateTime credentialsExpirationDate;

    @Column(name = "is_account_non_expired")
    private boolean isAccountNonExpired;

    @Column(name = "is_account_non_locked")
    private boolean isAccountNonLocked;

    @Column(name = "is_credentials_non_expired")
    private boolean isCredentialsNonExpired;

    @Column(name = "is_enabled")
    private boolean isEnabled;

    @JsonIgnoreProperties("user")
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Role> roles;
}
