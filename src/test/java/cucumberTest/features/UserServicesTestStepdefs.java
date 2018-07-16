package cucumberTest.features;

import br.com.douglasffilho.oauthapplication.OAuthApplication;
import br.com.douglasffilho.oauthapplication.entities.User;
import br.com.douglasffilho.oauthapplication.models.UserTestModel;
import br.com.douglasffilho.oauthapplication.services.UserService;
import cucumber.api.java.pt.Dado;
import cucumber.api.java.pt.Entao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

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

    @Dado("^a criacao do usuario padrao$")
    public void aCriacaoDoUsuarioPadrao() {
        final User created = this.userService.createTestUser();

        assertNotNull(created);

        log.info(
                "M=UserServicesTestStepdefs.aCriacaoDoUsuarioPadrao, " +
                        "I= Usuario criado: {}", created.getUsername());
    }

    @Entao("^verificar se o usuario esta na base de dados com os seguintes dados$")
    public void verificarSeOUsuarioEstaNaBaseDeDadosComOsSeguintesDados(final List<UserTestModel> userTest) {
        final UserTestModel user = userTest.get(0);

        final User defaultUser = this.userService.findByUsername("Administrador");

        assertEquals(user.getNome(), defaultUser.getUsername());
        assertEquals(user.getEmail(), defaultUser.getEmail());
        assertEquals(user.getPassword(), defaultUser.getPassword());
    }
}
