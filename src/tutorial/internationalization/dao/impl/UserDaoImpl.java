package tutorial.internationalization.dao.impl;

import tutorial.internationalization.dao.UserDAO;
import tutorial.internationalization.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Ioana on 2/28/2016.
 */
@Repository
public class UserDaoImpl implements UserDAO {
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public User findById(Integer id) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("id", id);

        String sql = "SELECT * FROM users WHERE id=:id";

        User result = null;
        try {
            result = namedParameterJdbcTemplate
                    .queryForObject(sql, params, new UserMapper());
        } catch (EmptyResultDataAccessException e) {
            // do nothing, return null
        }

        return result;

    }

    @Override
    public List<User> findAll() {
        String sql = "SELECT * FROM users";
        List<User> result = namedParameterJdbcTemplate.query(sql, new UserMapper());
        return result;
    }

    @Override
    public void save(User user) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        String sql = "INSERT INTO USERS(NAME, COUNTRY) "
                + "VALUES ( :name, :country)";

        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("id", user.getId());
        paramSource.addValue("name", user.getName());
        paramSource.addValue("country", user.getCountry());

        namedParameterJdbcTemplate.update(sql, paramSource, keyHolder);
        user.setId(keyHolder.getKey().intValue());
    }

    @Override
    public void update(User user) {
        String sql = "UPDATE USERS SET NAME=:name,COUNTRY=:country WHERE id=:id";
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("id", user.getId());
        paramSource.addValue("name", user.getName());
        paramSource.addValue("country", user.getCountry());

        namedParameterJdbcTemplate.update(sql, paramSource);

    }


    @Autowired
    public void setNamedParameterJdbcTemplate(
            NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    private static final class UserMapper implements RowMapper<User> {
        @Override
        public User mapRow(ResultSet resultSet, int i) throws SQLException {
            User user = new User();
            user.setId(resultSet.getInt("id"));
            user.setName(resultSet.getString("name"));
            user.setCountry(resultSet.getString("country"));
            return user;
        }
    }

}
