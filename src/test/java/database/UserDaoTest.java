package database;

import static org.assertj.core.api.Assertions.assertThat;

import java.sql.SQLException;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

@Disabled
class UserDaoTest {

    private final UserDao userDao = new UserDao();

    @Test
    public void connection() {
        try (var connection = userDao.getConnection()) {
            assertThat(connection).isNotNull();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void addUser() {
        var user = new User("test3", "test3");
        userDao.addUser(user);
    }

    @Test
    public void findByUserId() {
        var user = userDao.findByUserId("test1");

        assertThat(user).isEqualTo(new User("test1", "test2"));
    }

    @Test
    public void update() {
        var user = userDao.findByUserId("test1");
        userDao.update(user);
    }
}
