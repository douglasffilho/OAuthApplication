package br.com.douglasffilho.oauthapplication.services.impl;

import br.com.douglasffilho.oauthapplication.dao.UserDao;
import br.com.douglasffilho.oauthapplication.entities.User;
import br.com.douglasffilho.oauthapplication.services.UserService;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

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
