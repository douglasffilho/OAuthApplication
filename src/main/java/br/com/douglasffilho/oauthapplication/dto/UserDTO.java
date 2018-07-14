package br.com.douglasffilho.oauthapplication.dto;

import br.com.douglasffilho.oauthapplication.utils.ProfileEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class UserDTO {

    @NotNull(message = "Informe o nome do usuário.")
    private String name;

    @NotNull(message = "Infome o e-mail do usuário.")
    private String email;

    @NotNull(message = "Informe uma senha válida.")
    private String password;

    @NotNull(message = "O telefone do usuário é necessário.")
    private String phone;

    @NotNull(message = "Informe o nível de acesso deste usuário.")
    private List<ProfileEnum> roles;

}
