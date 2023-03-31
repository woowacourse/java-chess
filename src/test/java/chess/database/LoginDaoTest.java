package chess.database;

import static org.assertj.core.api.Assertions.assertThat;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Savepoint;

import chess.domain.game.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LoginDaoTest {

    private LoginDao loginDao;
    private Connection connection;

    @BeforeEach
    void setUp() {
        Database database = new Database(DatabaseName.TEST);
        connection = database.getConnection();
        assert connection != null;
        loginDao = new LoginDao(connection);
    }

    @Test
    void get_user_by_id_success() {
        try {
            connection.setAutoCommit(false);
            Savepoint savepoint = connection.setSavepoint();
            ChessTest.createMockUser(connection, "odo27", "mangmoong");
            ChessTest.createMockGame(connection, "100", "odo27");
            Assertions.assertThatCode(() -> loginDao.getUserById("odo27"))
                    .doesNotThrowAnyException();
            connection.rollback(savepoint);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    void get_user_by_id_has_id_and_nickname() {
        try {
            connection.setAutoCommit(false);
            Savepoint savepoint = connection.setSavepoint();
            ChessTest.createMockUser(connection, "odo27", "mangmoong");
            ChessTest.createMockGame(connection, "100", "odo27");
            User user = loginDao.getUserById("odo27");
            assertThat(user.getId()).isEqualTo("odo27");
            assertThat(user.getNickname()).isEqualTo("mangmoong");
            connection.rollback(savepoint);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    void add_user() {
        try {
            connection.setAutoCommit(false);
            Savepoint savepoint = connection.setSavepoint();
            ChessTest.createMockUser(connection, "odo27", "mangmoong");
            ChessTest.createMockGame(connection, "100", "odo27");
            User user = new User("abc", "hello");
            loginDao.addUser(user);
            User abc = loginDao.getUserById("abc");
            assertThat(abc.getId()).isEqualTo("abc");
            assertThat(abc.getNickname()).isEqualTo("hello");
            connection.rollback(savepoint);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    void get_user_by_id() {
        try {
            connection.setAutoCommit(false);
            Savepoint savepoint = connection.setSavepoint();
            User user = loginDao.getUserById("abc");
            assertThat(user).isEqualTo(null);
            connection.rollback(savepoint);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
