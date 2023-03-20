package database;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class UserDaoTest {

    private final UserDao userDao = new UserDao();

    @BeforeEach
    void setUp() throws SQLException {
        try (final var connection = userDao.getConnection()) {
            userDao.clear();
        }
    }

    @Test
    void connection() throws SQLException {
        try (final var connection = userDao.getConnection()) {
            assertThat(connection).isNotNull();
        }
    }

    @Test
    void addUser() {
        final var user = new User("testUserId", "testUser");
        userDao.addUser(user);
    }

    @Test
    void findByUserId() {
        final var user = new User("testUserId", "testUser");
        userDao.addUser(user);
        final var user1 = userDao.findByUserId("testUserId");

        assertThat(user1).isEqualTo(new User("testUserId", "testUser"));
    }

    @Test
    void updateUserName() {
        final var newName = "updatedName";
        final var user = new User("12", "oldName");
        userDao.addUser(user);

        userDao.updateUserName(user, newName);

        User find = userDao.findByUserId("12");

        Assertions.assertThat(find.getName()).isEqualTo("updatedName");
    }

    @Test
    void deleteUser() {
        userDao.addUser(new User("12", "delete"));
        User byUserId = userDao.findByUserId("12");
        final var user = new User("12", "delete");

        User user1 = userDao.deleteUser(user);

        Assertions.assertThat(byUserId).isEqualTo(user);
    }
}
