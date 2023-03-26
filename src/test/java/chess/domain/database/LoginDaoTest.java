package chess.domain.database;

import static org.assertj.core.api.Assertions.assertThat;

import java.sql.Connection;

import chess.domain.game.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LoginDaoTest {

    private final LoginDao loginDao = new LoginDao(Database.TEST);

    @BeforeEach
    void setUp() {
        Connection connection = loginDao.getConnection();
        assert connection != null;
        ChessTest.clearAll(connection);
        ChessTest.createMockUser(connection, "odo27", "mangmoong");
        ChessTest.createMockGame(connection, "100", "odo27");
    }

    @Test
    void connection() {
        assertThat(loginDao.getConnection()).isNotNull();
    }

    @Test
    void get_user_by_id_success() {
        Assertions.assertThatCode(() -> loginDao.getUserById("odo27"))
                .doesNotThrowAnyException();
    }

    @Test
    void get_user_by_id_has_id_and_nickname() {
        User user = loginDao.getUserById("odo27");
        assertThat(user.getId()).isEqualTo("odo27");
        assertThat(user.getNickname()).isEqualTo("mangmoong");
    }

    @Test
    void add_user() {
        User user = new User("abc", "hello");
        loginDao.addUser(user);
        User abc = loginDao.getUserById("abc");
        assertThat(abc.getId()).isEqualTo("abc");
        assertThat(abc.getNickname()).isEqualTo("hello");
    }

    @Test
    void get_user_by_id() {
        User user = loginDao.getUserById("abc");
        assertThat(user).isEqualTo(null);
    }
}
