package chess.repository;

import chess.domain.player.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;

public class UserDAOTest {

    private static final String testName1 = "testName1";
    private static final String testName2 = "testName2";
    public static final User TEST_USER1 = new User(testName1);
    public static final User TEST_USER2 = new User(testName2);

    private final DBConnector DBConnector = new DBConnector();

    private UserDAO userDAO;

    @BeforeEach
    void setup() {
        userDAO = new UserDAO(DBConnector);
    }

    @Test
    void crud() throws SQLException {
        userDAO.addUser(TEST_USER1);

        userDAO.updateByName(TEST_USER1.getName(), TEST_USER2);

        assertThat(userDAO.findUserByName(testName2).get()).isEqualTo(TEST_USER2);
    }

    @AfterEach
    void deleteUser() throws SQLException {
        userDAO.deleteByName(testName2);
    }
}