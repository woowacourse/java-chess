package chess.dao;

import static chess.TestUtils.*;
import static org.assertj.core.api.AssertionsForClassTypes.*;

import java.sql.Connection;
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

public class BoardDaoTest {
    private static final int TEST_BOARD_ID = 2;
    Map<Square, Piece> board;
    ChessGame chessGame;
    Connection connection;

    @BeforeEach
    void setUp() {
        board = Map.of(new Square("a1"), WHITE_PAWN, new Square("a2"), BLACK_QUEEN);
        chessGame = new ChessGame(new Board(board), Color.WHITE);
        connection = DBConnector.getConnection();
        new ChessGameDao().save(chessGame, TEST_BOARD_ID, connection);
    }

    @Test
    @DisplayName("정상적으로 찾아지는지 확인")
    void findAllPiecesOfBoard() {
        final BoardDao boardDao = new BoardDao();
        boardDao.save(chessGame.getBoard(), TEST_BOARD_ID, connection);
        Board dbBoard = boardDao.findAllPiecesOfBoard(TEST_BOARD_ID, connection);
        assertThat(dbBoard.getBoard().get(new Square("a1"))).isEqualTo(WHITE_PAWN);
        assertThat(dbBoard.getBoard().get(new Square("a2"))).isEqualTo(BLACK_QUEEN);

    }

    @AfterEach
    void reset() {
        new ChessGameDao().remove(new BoardDao(), TEST_BOARD_ID, connection);
        DBConnector.closeConnection(connection);
    }
}
