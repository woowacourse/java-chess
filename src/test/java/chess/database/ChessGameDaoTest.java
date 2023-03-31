package chess.database;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Savepoint;

import chess.domain.game.ChessGame;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ChessGameDaoTest {

    private Connection connection;

    @BeforeEach
    void setUp() {
        Database database = new Database(DatabaseName.TEST);
        connection = database.getConnection();
        assert connection != null;
    }

    @Test
    void save() {
        ChessGameDao chessGameDao = new ChessGameDao(connection);
        try {
            connection.setAutoCommit(false);
            Savepoint savepoint = connection.setSavepoint();
            ChessTest.createMockUser(connection, "odo27", "mangmoong");
            ChessTest.createMockGame(connection, "100", "odo27");
            ChessGame chessGame = new ChessGame();
            Assertions.assertThatCode(() -> chessGameDao.save("100", chessGame))
                    .doesNotThrowAnyException();
            connection.rollback(savepoint);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
