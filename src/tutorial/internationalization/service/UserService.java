package tutorial.internationalization.service;

import tutorial.internationalization.model.User;

import java.util.List;

/**
 * Created by Ioana on 2/28/2016.
 */
public interface UserService {
    User findById(Integer id);

    List<User> findAll();

    void saveOrUpdate(User user);
}
