package database;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

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
    public void updateByUserId() {
        userDao.updateByUserId("testUserId", "babo");
    }

    @Test
    public void deleteByUserId() {
        userDao.deleteByUserId("testUserId");
    }
}
