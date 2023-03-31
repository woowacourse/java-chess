package database;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class UserDaoTest {

    private final UserDao userDao = new UserDao();

    @Test
    void connection() {
        final var connection = userDao.getConnection();
        assertThat(connection).isNotNull();
    }

    @Test
    void addUser() {
        final var user = new User("testUserId", "testUser");
        userDao.addUser(user);
    }

    @Test
    public void findByUserId() {
        final var user = userDao.findByUserId("testUserId");

        assertThat(user).isEqualTo(new User("testUserId", "testUser"));
    }

    @Test
    public void updateUserId() {
        userDao.updateId("testUserId", "test");
        final var user = userDao.findByUserId("testUserId");
        assertThat(user).isEqualTo(new User("testUserId", "test"));
    }

    @Test
    public void deleteUserId() {
        userDao.deleteId("testUserId");
        final var user = userDao.findByUserId("testUserId");
        assertThat(user).isEqualTo(null);
    }
}
