package chess.model.repository;

import static org.assertj.core.api.Assertions.assertThat;

import chess.model.domain.board.BoardSquare;
import chess.model.domain.board.CastlingSetting;
import chess.model.domain.board.ChessGame;
import chess.model.domain.piece.Color;
import chess.model.domain.piece.Pawn;
import chess.model.domain.piece.Piece;
import chess.model.domain.state.MoveSquare;
import chess.model.domain.state.MoveState;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ChessBoardDaoTest {

    private ChessBoardDao chessBoardDao;

    @BeforeEach
    public void setup() {
        chessBoardDao = ChessBoardDao.getInstance();
    }

    @DisplayName("Connection Test")
    @Test
    public void connection() {
        Connection con = chessBoardDao.getConnection();
        assertThat(Objects.nonNull(con)).isTrue();
    }

    @Test
    void closeConnection() {
    }

    @Test
    public void crud() throws SQLException {
        String gameId = "T01-20200405-001";

        assertThat(chessBoardDao.getBoard(gameId)).isEmpty();
        assertThat(chessBoardDao.getCastlingElements(gameId)).isEmpty();
        assertThat(chessBoardDao.getEnpassantBoard(gameId)).isEmpty();

        ChessGame chessGame = new ChessGame();
        Map<BoardSquare, Piece> board = chessGame.getChessBoard();
        Set<CastlingSetting> castlingElements = CastlingSetting.getCastlingElements();

        chessBoardDao.insert(gameId, board, castlingElements);

        assertThat(chessBoardDao.getBoard(gameId)).isEqualTo(board);
        assertThat(chessBoardDao.getCastlingElements(gameId)).isEqualTo(castlingElements);
        assertThat(chessBoardDao.getEnpassantBoard(gameId)).isEmpty();

        BoardSquare boardSquareBefore = BoardSquare.of("a2");
        Piece pieceBefore = Pawn.getPieceInstance(Color.WHITE);
        BoardSquare boardSquareAfter = BoardSquare.of("a3");

        chessBoardDao.deleteBoardSquare(gameId, boardSquareBefore);
        chessBoardDao.insertBoard(gameId, boardSquareAfter, pieceBefore);

        assertThat(chessGame.movePieceWhenCanMove(new MoveSquare("a2", "a3")))
            .isEqualTo(MoveState.SUCCESS);

        board = chessGame.getChessBoard();
        assertThat(chessBoardDao.getBoard(gameId)).isEqualTo(board);
        assertThat(chessBoardDao.getCastlingElements(gameId)).isEqualTo(castlingElements);
        assertThat(chessBoardDao.getEnpassantBoard(gameId)).isEmpty();

        chessBoardDao.delete(gameId);
        assertThat(chessBoardDao.getBoard(gameId)).isEmpty();
        assertThat(chessBoardDao.getCastlingElements(gameId)).isEmpty();
        assertThat(chessBoardDao.getEnpassantBoard(gameId)).isEmpty();
    }
}