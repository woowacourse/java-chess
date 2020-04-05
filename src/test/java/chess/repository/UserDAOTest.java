package chess.repository;

import chess.domain.player.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;

class UserDAOTest {

    private static final String testName1 = "testName1";
    private static final String testName2 = "testName2";
    static final User TEST_USER1 = new User(testName1);
    static final User TEST_USER2 = new User(testName2);

    private final RepositoryUtil repositoryUtil = new RepositoryUtil();

    private UserDAO userDAO;

    @BeforeEach
    void setup() {
        userDAO = new UserDAO(repositoryUtil);
    }

    @Test
    void crud() throws SQLException {
        userDAO.addUser(new User(testName1));

        userDAO.updateByName(testName1, new User(testName2));

        assertThat(userDAO.findUserByName(testName2)).isEqualTo(new User(testName2));
    }

    @AfterEach
    void deleteUser() throws SQLException {
        userDAO.deleteByName(testName2);
    }
}