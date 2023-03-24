package chess.dao;

import static org.assertj.core.api.Assertions.assertThat;

import java.sql.SQLException;
import org.junit.jupiter.api.Test;

class ChessDaoTest {
    private final ChessDao userDao = new ChessDao();

    @Test
    public void connection() {
        try (final var connection = userDao.getConnection()) {
            assertThat(connection).isNotNull();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

//    @Test
//    public void addUser() {
//        final var user = new User("id", "name");
//        userDao.addUser(user);
//    }

}