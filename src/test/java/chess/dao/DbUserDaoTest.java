package chess.dao;

import chess.domain.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class DbUserDaoTest {

    final DbUserDao userDao = new DbUserDao();

    @BeforeEach
    void setUp() {
        userDao.deleteAll();
    }

    @Test
    void connection() {
        Connection connection = userDao.getConnection();
        assertThat(connection).isNotNull();
    }

    @Test
    void saveAndFindById() {
        User user = new User(1L, "philz");
        userDao.save(user);
        User findUser = userDao.findById(1);
        assertThat(findUser).isEqualTo(user);
    }

    @Test
    void findAll() {
        userDao.save(new User(1L, "user a"));
        userDao.save(new User(2L, "user b"));
        List<User> Users = userDao.findAll();
        assertThat(Users.size()).isEqualTo(2);
    }

    @Test
    void deleteById() {
        userDao.save(new User(1L, "user a"));
        userDao.save(new User(2L, "user b"));
        userDao.deleteById(1);
        List<User> Users = userDao.findAll();
        assertThat(Users.size()).isEqualTo(1);
    }
}