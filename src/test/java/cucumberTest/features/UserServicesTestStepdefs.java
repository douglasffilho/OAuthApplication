package cucumberTest.features;

import br.com.douglasffilho.oauthapplication.OAuthApplication;
import br.com.douglasffilho.oauthapplication.dto.UserDTO;
import br.com.douglasffilho.oauthapplication.entities.Role;
import br.com.douglasffilho.oauthapplication.entities.User;
import br.com.douglasffilho.oauthapplication.models.UserTestModel;
import br.com.douglasffilho.oauthapplication.services.UserService;
import br.com.douglasffilho.oauthapplication.utils.ProfileEnum;
import br.com.douglasffilho.oauthapplication.utils.impl.UserFactory;
import cucumber.api.java.pt.Dado;
import cucumber.api.java.pt.Entao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@Slf4j
@ActiveProfiles({"test"})
@ContextConfiguration(classes = OAuthApplication.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class UserServicesTestStepdefs {

    @Autowired
    private UserService userService;

    private UserTestModel userTestModel;

    private User getValidUser() throws IllegalArgumentException {
        return UserFactory
                .builder()
                .userDTO(UserDTO
                        .builder()
                        .name(this.userTestModel.getNome())
                        .email(this.userTestModel.getEmail())
                        .phone(this.userTestModel.getPhone())
                        .password(this.userTestModel.getPassword())
                        .build())
                .build()
                .createValid();
    }

    @Dado("^a criacao do usuario com as seguintes informacoes$")
    public void aCriacaoDoUsuarioComAsSeguintesInformacoes(final List<UserTestModel> userTest) {
        this.userTestModel = userTest.get(0);

        final User user = this.getValidUser();

        User created = this.userService.save(user);
        created
                .setRoles(
                        Collections.singletonList(Role
                                .builder()
                                .role(ProfileEnum.getByName(this.userTestModel.getRole()))
                                .user(created)
                                .build())
                );
        created = this.userService.save(created);

        assertNotNull(created);
    }

    @Entao("^verificar se o usuario esta na base de dados com os mesmo dados$")
    public void verificarSeOUsuarioEstaNaBaseDeDadosComOsMesmoDados() {
        final User found = this.userService.findByEmail(this.userTestModel.getEmail());

        final User validator = this.getValidUser();

        validator.setId(found.getId());
        validator
                .setRoles(
                        Collections.singletonList(Role
                                .builder()
                                .role(ProfileEnum.getByName(this.userTestModel.getRole()))
                                .user(found)
                                .build())
                );

        assertEquals(validator, found);

    }
}
