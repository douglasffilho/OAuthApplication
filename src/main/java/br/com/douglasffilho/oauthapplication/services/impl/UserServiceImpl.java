package br.com.douglasffilho.oauthapplication.services.impl;

import br.com.douglasffilho.oauthapplication.dao.UserDao;
import br.com.douglasffilho.oauthapplication.dto.UserDTO;
import br.com.douglasffilho.oauthapplication.entities.Role;
import br.com.douglasffilho.oauthapplication.entities.User;
import br.com.douglasffilho.oauthapplication.services.UserService;
import br.com.douglasffilho.oauthapplication.utils.ProfileEnum;
import br.com.douglasffilho.oauthapplication.utils.impl.UserFactory;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public User createDefaultUser() throws ServiceException {
        try {
            final User user = UserFactory
                    .builder()
                    .userDTO(UserDTO
                            .builder()
                            .name(System.getenv("DEFAULT_SYSTEM_ADMIN_USERNAME"))
                            .email(System.getenv("DEFAULT_SYSTEM_ADMIN_EMAIL"))
                            .phone(System.getenv("DEFAULT_SYSTEM_ADMIN_PHONE"))
                            .password(System.getenv("DEFAULT_SYSTEM_ADMIN_PASSWORD"))
                            .build())
                    .build()
                    .createValid();

            final Optional<User> found = this.userDao.findByEmail(user.getEmail());

            if (found.isPresent())
                return found.get();

            final User newUser = this.save(user);
            newUser.setRoles(
                    Collections.singletonList(Role
                            .builder()
                            .role(ProfileEnum.ROLE_ADMIN)
                            .user(newUser)
                            .build())
            );

            return newUser;
        } catch (final Exception ex) {
            throw new ServiceException("Erro ao tentar criar usuário padrão.", ex);
        }
    }

    @Override
    public User createTestUser() throws ServiceException {
        try {
            final User user = UserFactory
                    .builder()
                    .userDTO(UserDTO
                            .builder()
                            .name("Administrador")
                            .email("douglasf.filho@gmail.com")
                            .phone("16988487554")
                            .password("admin")
                            .build())
                    .build()
                    .createValid();

            final Optional<User> found = this.userDao.findByEmail(user.getEmail());

            if (found.isPresent())
                return found.get();

            final User newUser = this.save(user);
            newUser.setRoles(
                    Collections.singletonList(Role
                            .builder()
                            .role(ProfileEnum.ROLE_ADMIN)
                            .user(newUser)
                            .build())
            );

            return newUser;
        } catch (final Exception ex) {
            throw new ServiceException("Erro ao tentar criar usuário padrão.", ex);
        }
    }

    @Override
    public User save(final User user) throws ServiceException {
        try {
            return this.userDao.saveAndFlush(user);
        } catch (final NullPointerException npe) {
            throw new ServiceException("Erro ao tentar salvar usuário.");
        } catch (final Exception ex) {
            throw new ServiceException(ex.getMessage());
        }
    }

    @Override
    public List<User> list() throws ServiceException {
        try {
            return this.userDao.findAll();
        } catch (final NullPointerException npe) {
            throw new ServiceException("Nenhum usuário encontrado.");
        } catch (final Exception ex) {
            throw new ServiceException(ex.getMessage());
        }
    }

    @Override
    public User findByUsername(final String username) throws ServiceException {
        try {
            return this.userDao.findByUsername(username).orElseThrow(NullPointerException::new);
        } catch (final NullPointerException npe) {
            throw new ServiceException("Usuário não encontrado.");
        } catch (final Exception ex) {
            throw new ServiceException(ex.getMessage());
        }
    }

    @Override
    public User findByEmail(final String email) throws ServiceException {
        try {
            return this.userDao.findByEmail(email).orElseThrow(NullPointerException::new);
        } catch (final NullPointerException npe) {
            throw new ServiceException("Usuário não encontrado.");
        } catch (final Exception ex) {
            throw new ServiceException(ex.getMessage());
        }
    }

}
