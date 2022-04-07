package chess.dao;

import chess.domain.user.User;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class DbUserDaoTest {

    private static final DbUserDao userDao = new DbUserDao();

    @BeforeEach
    void setUp() {
        userDao.deleteAll();
    }

    @AfterAll
    static void afterAll() {
        userDao.deleteAll();
    }

    @Test
    void connection() {
        Connection connection = userDao.getConnection();
        assertThat(connection).isNotNull();
    }

    @Test
    void saveAndFindById() {
        User user = new User("philz", "성우");
        userDao.save(user);
        User findUser = userDao.findById("philz");
        assertThat(findUser).isEqualTo(user);
    }

    @Test
    void findAll() {
        userDao.save(new User("user a", "사람 a"));
        userDao.save(new User("user b", "사람 b"));
        List<User> Users = userDao.findAll();
        assertThat(Users.size()).isEqualTo(2);
    }

    @Test
    void update() {
        String id = "philz";
        User user = new User(id, "성우");
        userDao.save(user);
        userDao.update(new User(id, "필즈쿤"));
        User findUser = userDao.findById(id);
        assertThat(findUser.getName()).isEqualTo("필즈쿤");
    }

    @Test
    void deleteById() {
        userDao.save(new User("user a", "사람 a"));
        userDao.save(new User("user b", "사람 b"));
        userDao.deleteById("user a");
        List<User> Users = userDao.findAll();
        assertThat(Users.size()).isEqualTo(1);
    }
}