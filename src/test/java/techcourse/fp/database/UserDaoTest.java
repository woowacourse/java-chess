package techcourse.fp.database;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.SQLException;
import org.junit.jupiter.api.Test;

public final class UserDaoTest {

    private final UserDao userDao = new UserDao();

    @Test
    public void connection() {
        try (final Connection connection = userDao.getConnection()) {
            assertThat(connection).isNotNull();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void insert() {
        final User user = new User("testUserId", "testUser");

        userDao.insert(user);
    }

    @Test
    public void findByUserId() {
        final User user = userDao.findByUserId("testUserId");

        assertThat(user).isEqualTo(new User("testUserId", "testUser"));
    }

    @Test
    public void update() {
        final int affectedRows = userDao.update(new User("testUserId", "hi"));

        assertThat(affectedRows).isEqualTo(1);
    }

    @Test
    public void deleteUserById() {
        final int deletedUser = userDao.deleteByUserId("neo");

        assertThat(deletedUser).isEqualTo(1);
    }
}