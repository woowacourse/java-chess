package chess.database;

import chess.domain.Turn;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.assertj.core.api.Assertions.assertThat;

class ChessGameDaoTest {

    private ChessGameDao chessGameDao;

    @BeforeEach
    void setUp() {
        chessGameDao = new ChessGameDao();
    }

    @Test
    public void connection() {
        final Connection connection = chessGameDao.getConnection();
        assertThat(connection).isNotNull();
    }

    @Test
    public void insert() {
        chessGameDao.saveChessGame(Turn.WHITE);
    }

    @Test
    public void findChessBoard() {
        final var turn = chessGameDao.findChessGame();

        assertThat(turn).isNotNull();
    }

    @Test
    void delete() {
        chessGameDao.deleteChessGame();
        final Turn turn = chessGameDao.findChessGame();

        assertThat(turn).isNull();
    }
}
