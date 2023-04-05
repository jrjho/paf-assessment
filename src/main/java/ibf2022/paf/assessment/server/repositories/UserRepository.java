package ibf2022.paf.assessment.server.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import ibf2022.paf.assessment.exceptions.Exceptions;
import ibf2022.paf.assessment.server.models.User;

@Repository
public class UserRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    private final String FIND_BY_USER_SQL = "select * from user where username = ?";

    private final String INSERT_USER_SQL = "insert into user (user_id, username, name) values (?, ?, ?)";

    public Optional<User> findUserByUsername(String username) {

        try {
            User user = jdbcTemplate.queryForObject(FIND_BY_USER_SQL, BeanPropertyRowMapper.newInstance(User.class),
                    username);

            return Optional.of(user);

        } catch (Exception e) {
            return Optional.empty();
        }
    }

    public String insertUser(User user) {

        String userId = UUID.randomUUID().toString().substring(0, 8);
        String username = user.getUsername();

        if (username.matches(".*[^a-zA-Z0-9].*"))
            throw new Exceptions(
                    "Username can only contain letters and numbers. No spaces or special characters allowed");

        int check = jdbcTemplate.update(INSERT_USER_SQL, userId, username, user.getName());

        if (check > 0)
            return userId;
        else
            return null;
    }

}
