package br.com.douglasffilho.oauthapplication.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class UserTestModel {

    private String nome;

    private String email;

    private String password;

    private String phone;

    private String role;
}
