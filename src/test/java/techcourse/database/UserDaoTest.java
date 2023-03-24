package techcourse.database;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNoException;

import java.sql.SQLException;
import org.junit.jupiter.api.Test;

class UserDaoTest {

    private final UserDao userDao = new UserDao();

    @Test
    public void connection() throws SQLException {
        try (final var connection = userDao.getConnection()) {
            assertThat(connection).isNotNull();
        }
    }

    @Test
    public void addUser() {
        final var user = new User("testUserId", "testUser");
        userDao.insert(user);
    }

    @Test
    public void findByUserId() {
        final var user = userDao.findByUserId("testUserId");

        assertThat(user).isEqualTo(new User("testUserId", "testUser"));
    }

    @Test
    public void update() {
        final var user = new User("testUserId", "UpdatedTestUser");

        userDao.update(user);

        assertThat(user).isEqualTo(new User("testUserId", "UpdatedTestUser"));
    }

    @Test
    public void delete() {
        final var user = new User("testUserId", "UpdatedTestUser");

        assertThatNoException().isThrownBy(
                () -> userDao.delete(user)
        );
    }
}
