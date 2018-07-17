package br.com.douglasffilho.oauthapplication;

import br.com.douglasffilho.oauthapplication.rest.api.SwaggerConfig;
import br.com.douglasffilho.oauthapplication.security.WebSecurityConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@Slf4j
@SpringBootApplication
@Import({WebSecurityConfig.class, SwaggerConfig.class})
public class OAuthApplication {

    public static void main(final String[] args) {
        SpringApplication.run(OAuthApplication.class, args);
    }
}
