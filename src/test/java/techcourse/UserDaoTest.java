package techcourse;

import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;

public class UserDaoTest {
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
    public void editUser() {
        final var user = new User("testUserId", "testUser2");
        userDao.editUser("testUserId", user);
    }

    @Test
    public void removeUser() {
        userDao.removeUserByUserId("testUserId");

        final var user = userDao.findByUserId("testUserId");
        assertThat(user).isNull();
    }
}
