package br.com.douglasffilho.oauthapplication.rest.api.v1;

import br.com.douglasffilho.oauthapplication.dto.UserDTO;
import br.com.douglasffilho.oauthapplication.entities.User;
import br.com.douglasffilho.oauthapplication.rest.api.privateEndpoints.PrivateApiV1Endpoints;
import br.com.douglasffilho.oauthapplication.services.UserService;
import br.com.douglasffilho.oauthapplication.utils.impl.UserFactory;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@CrossOrigin(origins = "*")
@RequestMapping(PrivateApiV1Endpoints.PRIVATE_API_V1_USERS_ROOT_ENDPOINT)
@Api(value = "UserRestApi", description = "API para consultar e modificar usuários.")
public class UserRestApi {

    @Autowired
    private UserService userService;

    @PreAuthorize("hasAnyRole('ADMIN')")
    @RequestMapping(value = "", method = RequestMethod.GET)
    @ApiOperation(value = "Lista com os usuários do sistema")
    @ApiImplicitParams({
            @ApiImplicitParam(
                    name = "Authorization",
                    value = "Authorization token",
                    required = true,
                    dataType = "string",
                    paramType = "header"
            )
    })
    public List<User> usuarios(final HttpServletResponse response) throws IOException {
        try {
            return new ArrayList<>(this.userService.list())
                    .stream()
                    .peek(u -> u.setPassword("<secret>"))
                    .collect(Collectors.toList());
        } catch (final ServiceException ex) {
            log.error("M=usuarios, E=Erro ao tentar obter usuarios. {}", ex.getMessage(), ex);

            response.sendError(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
            return new ArrayList<>();
        }
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @ApiOperation(value = "Obter dados de usuário por nome")
    @ApiImplicitParams({
            @ApiImplicitParam(
                    name = "Authorization",
                    value = "Authorization token",
                    required = true,
                    dataType = "string",
                    paramType = "header"
            )
    })
    @RequestMapping(value = PrivateApiV1Endpoints.PRIVATE_API_V1_USERS_FIND_BY_NAME_ENDPOINT, method = RequestMethod.GET)
    public User obterUsuarioPorNome(@PathVariable("name") final String name, final HttpServletResponse response)
            throws IOException {
        try {
            final User found = this.userService.findByUsername(name);
            if (found != null)
                found.setPassword("<secret>");
            return found;
        } catch (final ServiceException ex) {
            log.error("M=obterUsuarioPorNome, E=Erro ao tentar encontrar user. {}", ex.getMessage(), ex);

            response.sendError(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
            return new User();
        }
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @ApiOperation(value = "Adicionar usuário")
    @ApiImplicitParams({
            @ApiImplicitParam(
                    name = "Authorization",
                    value = "Authorization token",
                    required = true,
                    dataType = "string",
                    paramType = "header"
            )
    })
    @RequestMapping(value = PrivateApiV1Endpoints.PRIVATE_API_V1_USERS_SAVE_ENDPOINT, method = RequestMethod.POST)
    public User salvarUsuario(@RequestBody @Valid final UserDTO usuario, final HttpServletResponse response)
            throws IOException {
        try {
            final User newUser = UserFactory
                    .builder()
                    .userDTO(usuario)
                    .build()
                    .createValid();
            return this.userService.save(newUser);
        } catch (final ServiceException ex) {
            log.error("M=salvarUsuario, E=Erro ao tentar salvar user. {}", ex.getMessage(), ex);

            response.sendError(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
            return null;
        }
    }
}
