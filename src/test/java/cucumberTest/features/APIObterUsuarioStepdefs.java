package cucumberTest.features;

import br.com.douglasffilho.oauthapplication.OAuthApplication;
import br.com.douglasffilho.oauthapplication.entities.User;
import br.com.douglasffilho.oauthapplication.rest.api.privateEndpoints.PrivateApiV1Endpoints;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import cucumber.api.java.pt.Dado;
import cucumber.api.java.pt.E;
import cucumber.api.java.pt.Entao;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@ContextConfiguration(classes = OAuthApplication.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@Slf4j
public class APIObterUsuarioStepdefs {

    private ResponseEntity<?> response;

    private int httpStatusCode;

    private void doGetForUserByName(final String name) {
        final RestTemplate restTemplate = new RestTemplate();

        final String url = "http://localhost:8080" + PrivateApiV1Endpoints.PRIVATE_API_V1_USERS_ROOT_ENDPOINT + "/" + name;

        try {
            this.response = restTemplate.getForEntity(url, User.class);

            this.httpStatusCode = this.response.getStatusCode().value();
        } catch (final HttpClientErrorException hcee) {
            this.httpStatusCode = hcee.getStatusCode().value();
        }
    }

    private void doGetUsers() {
        final RestTemplate restTemplate = new RestTemplate();

        final String url = "http://localhost:8080" + PrivateApiV1Endpoints.PRIVATE_API_V1_USERS_ROOT_ENDPOINT + "/";

        try {
            this.response = restTemplate.getForEntity(url, List.class);

            this.httpStatusCode = this.response.getStatusCode().value();
        } catch (final HttpClientErrorException hcee) {
            this.httpStatusCode = hcee.getStatusCode().value();
        }
    }

    @Dado("^usuario com nome \"(.*?)\"$")
    public void usuarioComNome(final String nome) {
        this.doGetForUserByName(nome);
        Assert.assertNotNull(this.response.getBody());
    }

    @Entao("^verifica seu email \"(.*?)\"$")
    public void verificaSeuEmail(final String email) {
        final User user = (User) this.response.getBody();
        Assert.assertEquals(user.getEmail(), email);
    }

    @Dado("^uma requisicao GET contra a API$")
    public void umaRequisicaoGETContraAAPI() {
        this.doGetUsers();
    }

    @Entao("^obter lista de usuarios$")
    public void obterListaDeUsuarios() {
        Assert.assertNotNull(this.response.getBody());
    }

    @E("^verificar se o usuario com id (\\d+) e nome \"([^\"]*)\" esta na lista$")
    public void verificarSeOUsuarioComIdENomeEstaNaLista(final Long id, final String nome) {
        final ObjectMapper mapper = new ObjectMapper();
        final List<User> lista = mapper.convertValue(this.response.getBody(), new TypeReference<List<User>>() {
        });

        final User achado = lista.stream().filter((u -> u.getId().equals(id))).findFirst().orElse(null);

        Assert.assertNotNull(achado);
        Assert.assertEquals(nome, achado.getUsername());
    }
}
