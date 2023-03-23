package database;

import static org.assertj.core.api.Assertions.assertThat;

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
        userDao.addUser(user);
    }

    @Test
    public void findByUserId() {
        final var user = userDao.findByUserId("testUserId");

        assertThat(user).isEqualTo(new User("testUserId", "testUser"));
    }

    @Test
    void updateName() {
        userDao.updateName("pobi", "gitJjang");
        final User user = userDao.findByUserId("pobiconan");

        assertThat(user.name()).isEqualTo("gitJjang");
    }

    @Test
    void deleteUserById() {
        final String userId = "pobiconan";
        userDao.deleteUserById(userId);
    }
}
