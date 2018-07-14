package br.com.douglasffilho.oauthapplication;

import br.com.douglasffilho.oauthapplication.rest.api.SwaggerConfig;
import br.com.douglasffilho.oauthapplication.security.WebSecurityConfig;
import br.com.douglasffilho.oauthapplication.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import javax.annotation.PostConstruct;

@Slf4j
@SpringBootApplication
@Import({WebSecurityConfig.class, SwaggerConfig.class})
public class OAuthApplication {

    @Autowired
    private UserService userService;

    @PostConstruct
    private void init() {
        try {
            this.userService.createDefaultUser();
        } catch (final ServiceException sex) {
            log.error("M=OAuthApplication.init, E=Erro ao tentar criar usuario padrao: {}", sex.getMessage(), sex);
        }
    }

    public static void main(final String[] args) {
        SpringApplication.run(OAuthApplication.class, args);
    }
}
