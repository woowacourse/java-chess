package techcourse.fp.mission;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class UserDAOTest {

    private final UserDAO userDao = new UserDAO();

    @Test
    public void connection() throws SQLException {
        try (final var connection = userDao.getConnection()) {
            assertThat(connection).isNotNull();
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
    public void updateUser() {
        final var user = new User("testUserId", "updatedName");

        userDao.updateUser(user);
    }

    @Test
    public void deleteUser() {
        final var user = new User("testUserId", "testUser");
        userDao.deleteUser(user.userId());
    }
}
