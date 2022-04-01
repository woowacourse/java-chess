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

class RookMovingStrategyTest {

    @DisplayName("처음과 같은 위치로 이동할 수 없다.")
    @Test
    void isNotMovable_withSamePosition() {
        // given
        Board board = Board.createInitializedBoard();
        Optional<Piece> bishop = board.find(Position.from(XAxis.C, YAxis.ONE));

        // when
        boolean actual = bishop.get()
                .isAbleToMove(Position.from(XAxis.C, YAxis.ONE), Position.from(XAxis.C, YAxis.ONE));

        // then
        assertThat(actual).isFalse();
    }

    @DisplayName("Y축이 같은 위치로 이동할 수 있다.")
    @Test
    void isMovable_withSameYAxis() {
        // given
        Board board = Board.createInitializedBoard();
        Optional<Piece> rook = board.find(Position.from(XAxis.A, YAxis.ONE));

        // when
        boolean actual = rook.get().isAbleToMove(Position.from(XAxis.A, YAxis.ONE), Position.from(XAxis.A, YAxis.TWO));

        // then
        assertThat(actual).isTrue();
    }

    @DisplayName("X축이 같은 위치로 이동할 수 있다.")
    @Test
    void isMovable_withSameXAxis() {
        // given
        Board board = Board.createInitializedBoard();
        Optional<Piece> rook = board.find(Position.from(XAxis.A, YAxis.ONE));

        // when
        boolean actual = rook.get().isAbleToMove(Position.from(XAxis.A, YAxis.ONE), Position.from(XAxis.B, YAxis.ONE));

        // then
        assertThat(actual).isTrue();
    }

    @DisplayName("X축과 Y축이 모두 다른 위치로 이동할 수 없다.")
    @Test
    void isNotMovable_withDifferentXAxisDifferentYAxis() {
        // given
        Board board = Board.createInitializedBoard();
        Optional<Piece> rook = board.find(Position.from(XAxis.A, YAxis.ONE));

        // when
        boolean actual = rook.get().isAbleToMove(Position.from(XAxis.A, YAxis.ONE), Position.from(XAxis.B, YAxis.TWO));

        // then
        assertThat(actual).isFalse();
    }
}
