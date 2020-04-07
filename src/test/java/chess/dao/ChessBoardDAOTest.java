package chess.dao;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.board.BoardSquare;
import chess.domain.board.CastlingSetting;
import chess.domain.board.ChessGame;
import chess.domain.piece.Color;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.state.MoveSquare;
import chess.domain.state.MoveState;
import java.sql.SQLException;
import java.util.Map;
import java.util.Set;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class ChessBoardDAOTest {

    private static ChessBoardDAO CHESS_BOARD_DAO;

    @BeforeAll
    public static void setup() {
        CHESS_BOARD_DAO = new ChessBoardDAO();
    }

    @Test
    public void crud() throws SQLException, IllegalAccessException {
        String gameId = "T01-20200405-001";

        assertThatThrownBy(() -> CHESS_BOARD_DAO.getBoard(gameId))
            .isInstanceOf(IllegalAccessException.class);
        assertThatThrownBy(() -> CHESS_BOARD_DAO.getCastlingElements(gameId))
            .isInstanceOf(IllegalAccessException.class);
        assertThat(CHESS_BOARD_DAO.getEnpassantBoard(gameId)).isEmpty();

        ChessGame chessGame = new ChessGame();
        Map<BoardSquare, Piece> board = chessGame.getChessBoard();
        Set<CastlingSetting> castlingElements = CastlingSetting.getCastlingElements();

        CHESS_BOARD_DAO.insert(gameId, board, castlingElements);

        assertThat(CHESS_BOARD_DAO.getBoard(gameId)).isEqualTo(board);
        assertThat(CHESS_BOARD_DAO.getCastlingElements(gameId)).isEqualTo(castlingElements);
        assertThat(CHESS_BOARD_DAO.getEnpassantBoard(gameId)).isEmpty();

        BoardSquare boardSquareBefore = BoardSquare.of("a2");
        Piece pieceBefore = Pawn.getPieceInstance(Color.WHITE);
        BoardSquare boardSquareAfter = BoardSquare.of("a3");

        CHESS_BOARD_DAO.deleteBoard(gameId, boardSquareBefore);
        CHESS_BOARD_DAO.insertBoard(gameId, boardSquareAfter, pieceBefore);

        assertThat(chessGame.movePieceWhenCanMove(new MoveSquare("a2", "a3")))
            .isEqualTo(MoveState.SUCCESS);

        board = chessGame.getChessBoard();
        assertThat(CHESS_BOARD_DAO.getBoard(gameId)).isEqualTo(board);
        assertThat(CHESS_BOARD_DAO.getCastlingElements(gameId)).isEqualTo(castlingElements);
        assertThat(CHESS_BOARD_DAO.getEnpassantBoard(gameId)).isEmpty();

        CHESS_BOARD_DAO.delete(gameId);
        assertThatThrownBy(() -> CHESS_BOARD_DAO.getBoard(gameId))
            .isInstanceOf(IllegalAccessException.class);
        assertThatThrownBy(() -> CHESS_BOARD_DAO.getCastlingElements(gameId))
            .isInstanceOf(IllegalAccessException.class);
        assertThat(CHESS_BOARD_DAO.getEnpassantBoard(gameId)).isEmpty();
    }
}