package database;

import static org.assertj.core.api.Assertions.assertThat;

import chess.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class UserDaoTest {

    private final UserDao userDao = new UserDao();

    @BeforeEach
    void reset() {
        userDao.clear();
        userDao.addUser(new User("updateTestId", "updateTestNewName"));
        userDao.addUser(new User("findTestId", "findTest"));
        userDao.addUser(new User("deleteTestId", "deleteTest"));
    }

    @Test
    void connection() {
        assertThat(userDao.getConnection()).isNotNull();
    }

    @Test
    void addUser() {
        int affected = userDao.addUser(new User("addTestId", "addTest"));

        assertThat(affected).isEqualTo(1);
    }

    @Test
    void findByUserId() {
        final User user = userDao.findByUserId("findTestId");

        assertThat(user).isEqualTo(new User("findTestId", "findTest"));
    }

    @Test
    void updateUser() {
        int affected = userDao.updateUser(new User("updateTestId", "updateTestNewName"));

        assertThat(affected).isEqualTo(1);
    }

    @Test
    void deleteUser() {
        int affected = userDao.deleteByUserId("deleteTestId");

        assertThat(affected).isEqualTo(1);
    }
}
