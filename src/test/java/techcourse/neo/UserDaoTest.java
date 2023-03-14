package techcourse.neo;

import static java.sql.DriverManager.getConnection;
import static org.assertj.core.api.Assertions.assertThat;

import java.sql.SQLException;
import org.junit.jupiter.api.Test;

class UserDaoTest {

    private final UserDao userDao = new UserDao();

    @Test
    public void connection() {
        try (final var connection = userDao.getConnection()) {
            assertThat(connection).isNotNull();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void addUser() {
        final var user = new User("testUserId", "testUser");
        userDao.addUser(user);
    }

    @Test
    public void findByUserId() {
        final var user = userDao.findByUserId("testUserId");
        assertThat(user).isEqualTo(new User("testUserId", "testUser"));
    }

    @Test
    public void update() {
        userDao.updateUser("testUserId", "pobi");
    }

    @Test
    public void delete() {
        userDao.deleteUserById("testUserId");
    }
}
