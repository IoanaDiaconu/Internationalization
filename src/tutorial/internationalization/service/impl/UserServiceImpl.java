package tutorial.internationalization.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import tutorial.internationalization.dao.UserDAO;
import tutorial.internationalization.model.User;
import org.springframework.stereotype.Service;
import tutorial.internationalization.service.UserService;

import java.util.List;

/**
 * Created by Ioana on 2/28/2016.
 */
@Service("userService")
public class UserServiceImpl implements UserService {
    @Autowired
    UserDAO userDAO;

    @Override
    public User findById(Integer id) {
        return userDAO.findById(id);
    }

    @Override
    public List<User> findAll() {
        return userDAO.findAll();
    }

    @Override
    public void saveOrUpdate(User user) {
        if (findById(user.getId()) == null) {
            userDAO.save(user);
        } else {
            userDAO.update(user);
        }

    }
}
