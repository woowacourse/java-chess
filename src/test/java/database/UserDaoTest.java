package database;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class UserDaoTest {

    private static final UserDao userDao = new UserDao();

    @BeforeEach
    void cleanUp(){
        userDao.cleanUserTable();
        addUser();
    }

    @Test
    public void connection() throws SQLException {
        try (final var connection = userDao.getConnection()) {
            assertThat(connection).isNotNull();
        }
    }

    static void addUser() {
        final User user = new User("bebe1", "bebe1");
        userDao.addUser(user);
    }

    @Test
    public void findByUserId() {
        final User user = userDao.findByUserId("bebe1");
        assertThat(user).isEqualTo(new User("bebe1", "bebe1"));
    }

    @Test
    public void notFound() {
        assertThat(userDao.findByUserId("bebe")).isNull();
    }

    @Test
    void update() {
        final int affectedRows = userDao.update(new User("bebe1", "one"));
        assertThat(affectedRows).isEqualTo(1);
    }

    @Test
    void deleteByUserId() {
        assertThat(userDao.deleteByUserId("bebe1")).isEqualTo(false);
    }
}
