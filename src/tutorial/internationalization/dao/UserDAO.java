package tutorial.internationalization.dao;

import tutorial.internationalization.model.User;

import java.util.List;

/**
 * Created by Ioana on 2/28/2016.
 */
public interface UserDAO {
    User findById(Integer id);

    List<User> findAll();

    void save(User user);

    void update(User user);
}
