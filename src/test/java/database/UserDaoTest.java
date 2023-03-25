package database;

import static org.assertj.core.api.Assertions.assertThat;

import chess.infra.connection.JdbcTemplate;
import chess.infra.connection.TestConnectionPool;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(OrderAnnotation.class)
class UserDaoTest {

    private final UserDao userDao = new UserDao(new JdbcTemplate(new TestConnectionPool()));

    @Test
    @Order(1)
    void addUser() {
        var user = new User("test1", "name1");
        userDao.addUser(user);
    }

    @Test
    @Order(2)
    void update() {
        var user = userDao.findByUserId("test1");
        userDao.update(user, "name2");
    }

    @Test
    @Order(3)
    void findByUserId() {
        var user = userDao.findByUserId("test1");

        assertThat(user).isEqualTo(new User("test1", "name2"));
    }

    @Test
    @Order(4)
    void delete() {
        userDao.delete("test1");
    }
}
