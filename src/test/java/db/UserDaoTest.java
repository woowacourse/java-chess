package db;

import static org.assertj.core.api.Assertions.*;

import java.sql.SQLException;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(value = MethodOrderer.OrderAnnotation.class)
class UserDaoTest {

    private final UserDao userDao = new UserDao();

    @Test
    @Order(1)
    public void connection() throws SQLException {
        try (final var connection = userDao.getConnection()) {
            assertThat(connection).isNotNull();
        }
    }

    @Test
    @Order(2)
    public void addUser() {
        final var user = new User("testUserId", "testUser");
        userDao.addUser(user);
    }

    @Test
    @Order(3)
    public void findByUserId() {
        final var user = userDao.findByUserId("testUserId");

        assertThat(user).isEqualTo(new User("testUserId", "testUser"));
    }

    @Test
    @Order(4)
    public void updateUserId() {
        final var user = new User("testUserId", "testUser");
        userDao.updateUserId(user, "kero");

        assertThat(userDao.findByUserId("kero")).isNotNull();
    }

    @Test
    @Order(5)
    public void deleteUserId() {
        final var user = new User("kero", "testUser");
        userDao.deleteUserId(user);

        assertThat(userDao.findByUserId("kero")).isNull();
    }
}
