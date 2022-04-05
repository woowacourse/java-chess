package chess.dao;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.game.state.ChessGame;
import chess.domain.game.state.Ready;
import java.sql.Connection;
import org.junit.jupiter.api.Test;

public class ChessGameDaoTest {

    @Test
    void connection() {
        final ChessGameDao memberDao = new ChessGameDao();
        final Connection connection = memberDao.getConnection();
        assertThat(connection).isNotNull();
    }

    @Test
    void save() {
        final ChessGameDao chessGameDao = new ChessGameDao();
        ChessGame chessGame = new Ready();
        chessGameDao.save(chessGame);
    }
}
