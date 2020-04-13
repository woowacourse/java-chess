package chess.model.repository;

import static org.assertj.core.api.Assertions.assertThat;

import chess.model.domain.board.BoardSquare;
import chess.model.domain.board.ChessGame;
import chess.model.domain.piece.Color;
import chess.model.domain.piece.Pawn;
import chess.model.domain.piece.Piece;
import chess.model.domain.state.MoveSquare;
import chess.model.domain.state.MoveState;
import java.util.Map;
import java.util.stream.Collectors;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ChessBoardDaoTest {

    private ChessBoardDao chessBoardDao;

    @BeforeEach
    public void setup() {
        chessBoardDao = ChessBoardDao.getInstance();
    }

    @Test
    public void crud() {

        int gameId = 1;

        assertThat(chessBoardDao.getBoard(gameId)).isEmpty();
        assertThat(chessBoardDao.getCastlingElements(gameId)).isEmpty();
        assertThat(chessBoardDao.getEnpassantBoard(gameId).getEnPassants()).isEmpty();

        ChessGame chessGame = new ChessGame();
        Map<BoardSquare, Piece> board = chessGame.getChessBoard();
        Map<BoardSquare, Boolean> castlingElements = board.keySet().stream()
            .collect(Collectors.toMap(boardSquare -> boardSquare, boardSquare -> false));
        BoardSquare castlingElement = BoardSquare.of("a1");
        castlingElements.put(castlingElement, true);
        chessBoardDao.insert(gameId, board, castlingElements);

        assertThat(chessBoardDao.getBoard(gameId)).isEqualTo(board);
        assertThat(chessBoardDao.getCastlingElements(gameId).size()).isEqualTo(1);
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
        assertThat(chessBoardDao.getCastlingElements(gameId).size()).isEqualTo(1);
        assertThat(chessBoardDao.getEnpassantBoard(gameId).getEnPassants()).isEmpty();

        chessBoardDao.delete(gameId);
        assertThat(chessBoardDao.getBoard(gameId)).isEmpty();
        assertThat(chessBoardDao.getCastlingElements(gameId)).isEmpty();
        assertThat(chessBoardDao.getEnpassantBoard(gameId).getEnPassants()).isEmpty();
    }
}