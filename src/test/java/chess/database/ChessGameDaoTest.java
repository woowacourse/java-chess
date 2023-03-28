package chess.database;

import java.sql.Connection;

import chess.domain.game.ChessGame;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ChessGameDaoTest {

    private final ChessGameDao chessGameDao = new ChessGameDao(DatabaseName.TEST);

    @BeforeEach
    void setUp() {
        Database database = new Database(DatabaseName.TEST);
        Connection connection = database.getConnection();
        assert connection != null;
        ChessTest.clearAll(connection);
        ChessTest.createMockUser(connection, "odo27", "mangmoong");
        ChessTest.createMockGame(connection, "100", "odo27");
    }

    @Test
    void save() {
        ChessGame chessGame = new ChessGame();
        Assertions.assertThatCode(() -> chessGameDao.save("100", chessGame))
                .doesNotThrowAnyException();
    }
}
