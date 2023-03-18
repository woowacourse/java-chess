package techcourse.fp.database;

import static org.assertj.core.api.Assertions.assertThat;

import java.sql.Connection;
import java.sql.SQLException;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
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

    @BeforeEach
    public void insert() {
        final User user = new User("testUserId", "testUser");

        userDao.insert(user);
    }

    @AfterEach
    public void deleteByUserId() {
        final int affectedUser = userDao.deleteByUserId("testUserId");

        assertThat(affectedUser).isEqualTo(1);
    }

    @Test
    public void findByUserId() {
        final User user = userDao.findByUserId("testUserId");

        assertThat(user).isEqualTo(new User("testUserId", "testUser"));
    }

    @Test
    public void update() {
        final int affectedRow = userDao.updateUser(new User("testUserId", "newTestName"));

        assertThat(affectedRow).isEqualTo(1);
    }
}