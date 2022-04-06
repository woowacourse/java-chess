package chess.dao;

import static chess.TestUtils.*;
import static org.assertj.core.api.Assertions.*;

import java.util.Map;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chess.domain.Board;
import chess.domain.ChessGame;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.position.Square;

public class ChessGameDaoTest {
    private static final int TEST_BOARD_ID = 2;
    Map<Square, Piece> board;
    ChessGame chessGame;

    @BeforeEach
    void setUp() {
        board = Map.of(new Square("a1"), WHITE_PAWN, new Square("a2"), BLACK_QUEEN);
        chessGame = new ChessGame(new Board(board), Color.WHITE);
    }

    @Test
    @DisplayName("board_id로 찾아지는지 확인")
    void find() {
        final ChessGameDao chessGameDao = new ChessGameDao();
        chessGameDao.save(chessGame, TEST_BOARD_ID, DBConnector.getConnection());
        assertThat(chessGameDao.find(TEST_BOARD_ID, DBConnector.getConnection()).getTurn()).isEqualTo("white");
    }

    @Test
    @DisplayName("board_id로 찾아지는지 확인")
    void update() {
        final ChessGameDao chessGameDao = new ChessGameDao();
        chessGameDao.save(chessGame, TEST_BOARD_ID, DBConnector.getConnection());
        chessGame = new ChessGame(new Board(board), Color.BLACK);
        chessGameDao.update(chessGame, TEST_BOARD_ID, DBConnector.getConnection());
        assertThat(chessGameDao.find(TEST_BOARD_ID, DBConnector.getConnection()).getTurn()).isEqualTo("black");
    }

    @AfterEach
    void reset() {
        new ChessGameDao().remove(TEST_BOARD_ID, DBConnector.getConnection());
    }
}
