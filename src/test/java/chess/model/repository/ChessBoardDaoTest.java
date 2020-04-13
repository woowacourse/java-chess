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
import java.sql.SQLException;
import java.util.Map;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ChessBoardDaoTest {

    private ChessBoardDao chessBoardDao;

    @BeforeEach
    public void setup() {
        chessBoardDao = ChessBoardDao.getInstance();
    }

    @Test
    public void crud() throws SQLException {

        int gameId = 1;

        assertThat(chessBoardDao.getBoard(gameId)).isEmpty();
        assertThat(chessBoardDao.getCastlingElements(gameId)).isEmpty();
        assertThat(chessBoardDao.getEnpassantBoard(gameId).getEnPassants()).isEmpty();

        ChessGame chessGame = new ChessGame();
        Map<BoardSquare, Piece> board = chessGame.getChessBoard();
        Set<CastlingSetting> castlingElements = CastlingSetting.getCastlingElements();

        chessBoardDao.insert(gameId, board, castlingElements);

        assertThat(chessBoardDao.getBoard(gameId)).isEqualTo(board);
        assertThat(chessBoardDao.getCastlingElements(gameId)).isEqualTo(castlingElements);
        assertThat(chessBoardDao.getEnpassantBoard(gameId).getEnPassants()).isEmpty();

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
        assertThat(chessBoardDao.getEnpassantBoard(gameId).getEnPassants()).isEmpty();

        chessBoardDao.delete(gameId);
        assertThat(chessBoardDao.getBoard(gameId)).isEmpty();
        assertThat(chessBoardDao.getCastlingElements(gameId)).isEmpty();
        assertThat(chessBoardDao.getEnpassantBoard(gameId).getEnPassants()).isEmpty();
    }
}