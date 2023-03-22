package database;

import static org.assertj.core.api.Assertions.assertThat;

import chess.database.User;
import chess.database.UserDao;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

@Disabled
class UserDaoTest {

    private final UserDao userDao = new UserDao();

    @Test
    public void connection() {
        try (final var connection = userDao.getConnection()) {
            assertThat(connection).isNotNull();
        }
        catch (SQLException e) {
            return;
        }
    }

    @Test
    public void findByUserId() {
        final var user = userDao.findByUserId("testUserId");

        assertThat(user).isEqualTo(new User("testUserId", "testUser"));
    }
}
