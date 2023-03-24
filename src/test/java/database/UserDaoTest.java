package database;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

class UserDaoTest {
    private final UserDao userDao = new UserDao();

    @Test
    public void connection() {
        try (final var connection = userDao.getConnection()) {
            Assertions.assertThat(connection).isNotNull();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @BeforeEach
    void addUser() {
        final var user = new User("testUserId", "testUser");
        userDao.add(user);
    }

    @AfterEach
    public void deleteByUserId() {
        final var user = userDao.delete("testUserId");

        Assertions.assertThat(user).isEqualTo(1);
    }

    @Test
    public void findByUserId() {
        final var user = userDao.findByUserId("testUserId");

        Assertions.assertThat(user).isEqualTo(new User("testUserId", "testUser"));
    }

    @Test
    void update() {
        userDao.update(new User("testUserId", "power"));
        final var user = userDao.findByUserId("testUserId");

        Assertions.assertThat(user).isEqualTo(new User("testUserId", "power"));
    }
}
