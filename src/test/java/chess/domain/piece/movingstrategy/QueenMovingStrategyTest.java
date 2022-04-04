package chess.domain.piece.movingstrategy;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.board.Board;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import chess.domain.position.XAxis;
import chess.domain.position.YAxis;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class QueenMovingStrategyTest {

    @DisplayName("처음과 같은 위치로 이동할 수 없다.")
    @Test
    void isNotMovable_withSamePosition() {
        // given
        Board board = Board.createInitializedBoard();
        Optional<Piece> queen = board.find(Position.of(XAxis.D, YAxis.ONE));

        // when
        boolean actual = queen.get().isAbleToMove(Position.of(XAxis.D, YAxis.ONE), Position.of(XAxis.D, YAxis.ONE));

        // then
        assertThat(actual).isFalse();
    }

    @DisplayName("X축과 Y축이 모두 다른 위치로 이동할 수 있다.")
    @Test
    void isMovable_withDifferentXAxisDifferentYAxis() {
        // given
        Board board = Board.createInitializedBoard();
        Optional<Piece> queen = board.find(Position.of(XAxis.D, YAxis.ONE));

        // when
        boolean actual = queen.get().isAbleToMove(Position.of(XAxis.D, YAxis.ONE), Position.of(XAxis.E, YAxis.TWO));

        // then
        assertThat(actual).isTrue();
    }

    @DisplayName("Y축이 같은 위치로 이동할 수 있다.")
    @Test
    void isMovable_withSameYAxis() {
        // given
        Board board = Board.createInitializedBoard();
        Optional<Piece> queen = board.find(Position.of(XAxis.D, YAxis.ONE));

        // when
        boolean actual = queen.get().isAbleToMove(Position.of(XAxis.D, YAxis.ONE), Position.of(XAxis.D, YAxis.TWO));

        // then
        assertThat(actual).isTrue();
    }

    @DisplayName("X축이 같은 위치로 이동할 수 있다.")
    @Test
    void isMovable_withSameXAxis() {
        // given
        Board board = Board.createInitializedBoard();
        Optional<Piece> queen = board.find(Position.of(XAxis.D, YAxis.ONE));

        // when
        boolean actual = queen.get().isAbleToMove(Position.of(XAxis.D, YAxis.ONE), Position.of(XAxis.E, YAxis.ONE));

        // then
        assertThat(actual).isTrue();
    }

    @DisplayName("대각선과 수직이 아닌 위치로 이동할 수 없다.")
    @Test
    void isNotMovable_notOnDiagonal() {
        // given
        Board board = Board.createInitializedBoard();
        Optional<Piece> queen = board.find(Position.of(XAxis.D, YAxis.ONE));

        // when
        boolean actual = queen.get()
                .isAbleToMove(Position.of(XAxis.D, YAxis.ONE), Position.of(XAxis.E, YAxis.THREE));

        // then
        assertThat(actual).isFalse();
    }
}
