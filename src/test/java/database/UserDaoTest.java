package database;

import static org.assertj.core.api.Assertions.assertThat;

import java.sql.SQLException;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(OrderAnnotation.class)
class UserDaoTest {

    private final UserDao userDao = new UserDao();

    @Test
    @Order(1)
    void connection() {
        try (var connection = userDao.getConnection()) {
            assertThat(connection).isNotNull();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @Order(2)
    void addUser() {
        var user = new User("test1", "name1");
        userDao.addUser(user);
    }

    @Test
    @Order(3)
    void update() {
        var user = userDao.findByUserId("test1");
        userDao.update(user, "name2");
    }

    @Test
    @Order(4)
    void findByUserId() {
        var user = userDao.findByUserId("test1");

        assertThat(user).isEqualTo(new User("test1", "name2"));
    }

    @Test
    @Order(5)
    void delete() {
        userDao.delete("test1");
    }
}
