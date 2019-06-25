package chess.domain.board;

import chess.domain.piece.piecefigure.Knight;
import chess.domain.piece.pieceinfo.TeamType;
import chess.exception.NotMovablePositionException;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class BoardTest {
    @Test
    public void 체스Piece를_이동시키는_테스트() {
        Board board = BoardGenerator.createBoard(BoardInputForTest.EXAMPLE_BOARD);
        // Knight(Black)
        boolean result = board.movePiece(
                Position.of(Position.makeKey(0, 1)), Position.of(Position.makeKey(2, 2)));

        assertThat(result).isFalse();
        assertThat(board.getCurrentPiece(Position.of(Position.makeKey(2, 2)))).isEqualTo(Knight.of(TeamType.BLACK));
    }

    @Test
    public void King을_잡았을때_테스트() {
        Board board = BoardGenerator.createBoard(BoardInputForTest.CHECKMATE_BOARD);
        // pawn(White)
        boolean result = board.movePiece(
                Position.of(Position.makeKey(4, 3)), Position.of(Position.makeKey(3, 2)));

        assertThat(result).isTrue();
    }

    @Test
    public void 목적지로_이동하지_못하는_경우_예외처리() {
        Board board = BoardGenerator.createBoard(BoardInputForTest.EXAMPLE_BOARD);
        assertThrows(NotMovablePositionException.class, () -> {
            // Knight(Black)
            board.movePiece(Position.of(Position.makeKey(0, 1)), Position.of(Position.makeKey(1, 3)));
        });
    }

    @Test
    public void 점수_계산_테스트() {
        Board board = BoardGenerator.createBoard(BoardInputForTest.DUPLICATED_PAWN_BOARD);
        assertThat(board.calculateFinalScore(TeamType.WHITE)).isEqualTo(36.5);
    }
}