package techcourse.fp.database;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.sql.SQLException;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(value = MethodOrderer.OrderAnnotation.class)
public class UserDaoTest {
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
    public void updateUserName() {
        userDao.updateUser("testUserId", "newName");

        final var user = userDao.findByUserId("testUserId");

        assertThat(user).isEqualTo(new User("testUserId", "newName"));
    }

    @Test
    @Order(5)
    public void deleteUserById() {
        userDao.deleteUser("testUserId");

        final var user = userDao.findByUserId("testUserId");

        assertThat(user).isNull();
    }
}
