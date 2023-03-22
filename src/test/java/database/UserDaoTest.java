package database;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.Assertions.fail;

import java.sql.SQLException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class UserDaoTest {

    private final UserDao userDao = new UserDao();
    private final User user = new User("asdf", "sadf");

    @AfterEach
    void tearDown() {

    }

    @Test
    public void connection() {
        try (final var connection = userDao.getConnection()) {
            assertThat(connection).isNotNull();
        } catch (SQLException e) {
            fail("실패");
        }
    }

    @Test
    @DisplayName("Insert")
    void test_insert() {
        userDao.insert(user);

        assertThat(userDao.findByUserId("asdf")).isEqualTo(user);
    }

    @Test
    @DisplayName("findByUserId")
    void test_findByUserId() {
        userDao.insert(user);

        assertThat(userDao.findByUserId("asdf")).isEqualTo(user);
    }

    @Test
    @DisplayName("Not Found")
    void test_not_found() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> userDao.findByUserId("some"));
    }

    @Test
    @DisplayName("update")
    void test_update() {
        userDao.insert(user);
        User userToUpdate = userDao.findByUserId("asdf");

        userDao.update(userToUpdate, user.changeName("ffff"));

        assertThat(userDao.findByUserId("asdf")).isEqualTo(user.changeName("ffff"));
    }

    @Test
    @DisplayName("delete")
    void test_delete() {
        userDao.insert(user);
        User asdf = userDao.findByUserId("asdf");

        assertThat(userDao.delete(asdf)).isOne();

        assertThatIllegalArgumentException().isThrownBy(() -> userDao.findByUserId("asdf"));
    }
}