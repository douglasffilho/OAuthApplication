package br.com.douglasffilho.oauthapplication.security.services;

import br.com.douglasffilho.oauthapplication.entities.User;
import br.com.douglasffilho.oauthapplication.security.utils.JwtUserFactory;
import br.com.douglasffilho.oauthapplication.services.UserService;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        try {
            final User user = this.userService.findByUsername(username);
            return JwtUserFactory.create(user);
        } catch (final ServiceException ex1) {
            try {
                final User user = this.userService.findByEmail(username);
                return JwtUserFactory.create(user);
            } catch (final ServiceException ex2) {
                throw new UsernameNotFoundException("Usuário não encontrado.");
            }
        }
    }
}
