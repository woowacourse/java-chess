package database;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@Disabled
class UserDaoTest {

    private final UserDao userDao = new UserDao();

    @BeforeEach
    void init() {
        userDao.deleteAll();
    }

    @Test
    void connection() {
        try (final var connection = userDao.getConnection()) {
            assertThat(connection).isNotNull();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void addUser() {
        final var user = new User("testUserId", "testUser");
        userDao.addUser(user);
    }

    @Test
    void findByUserId() {
        //given
        final User user = new User("연어", "연어");
        userDao.addUser(user);

        //when
        final User foundUser = userDao.findByUserId("연어");

        //then
        assertThat(foundUser).isEqualTo(new User("연어", "연어"));
    }

    @Test
    void updateUserName() {
        //given
        final User user = new User("연어", "연어");
        userDao.addUser(user);

        //when
        userDao.updateUserName("연어", "먹고 싶다");

        //then
        assertThat(userDao.findByUserId("연어")).isEqualTo(new User("연어", "먹고 싶다"));
    }

    @Test
    void deleteByUserId() {
        final User user = new User("연어", "연어");
        userDao.addUser(user);

        //when
        userDao.deleteByUserId("연어");

        //then
        assertThat(userDao.findByUserId("연어")).isNull();
    }
}
