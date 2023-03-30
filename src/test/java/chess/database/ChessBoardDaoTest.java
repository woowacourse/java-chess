package chess.database;

import chess.domain.ChessBoardFactory;
import chess.domain.Square;
import chess.domain.chesspiece.Piece;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class ChessBoardDaoTest {

    private ChessBoardDao chessBoardDao;

    @BeforeEach
    void setUp() {
        chessBoardDao = new ChessBoardDao();
    }

    @Test
    public void connection() {
        final Connection connection = chessBoardDao.getConnection();
        assertThat(connection).isNotNull();
    }

    @Test
    public void insert() {
        final var chessBoard = ChessBoardFactory.generate();
        chessBoardDao.saveChessBoard(chessBoard);
    }

    @Test
    public void findChessBoard() {
        final var chessBoard = chessBoardDao.findChessBoard();

        assertThat(chessBoard).isNotNull();
    }

    @Test
    void delete() {
        chessBoardDao.deleteChessBoard();
        final Map<Square, Piece> pieces = chessBoardDao.findChessBoard();

        assertThat(pieces).isNull();
    }
}
