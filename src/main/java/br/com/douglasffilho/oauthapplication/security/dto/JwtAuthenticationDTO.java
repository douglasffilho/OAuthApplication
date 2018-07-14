package br.com.douglasffilho.oauthapplication.security.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Data
public class JwtAuthenticationDTO {

    @NotEmpty(message = "Por favor, informe seu e-mail.")
    @Email(message = "Por favor, informe um e-mail válido.")
    private String email;

    @NotEmpty(message = "Por favor, informe sua senha.")
    private String password;

}
