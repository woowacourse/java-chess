package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.board.Board;
import chess.domain.position.Position;
import chess.domain.position.XAxis;
import chess.domain.position.YAxis;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RookTest {

    @DisplayName("처음과 같은 위치로 이동할 수 없다.")
    @Test
    void isNotMovable_withSamePosition() {
        // given
        Board board = Board.createInitializedBoard();
        Optional<AbstractPiece> bishop = board.find(Position.from(XAxis.C, YAxis.ONE));

        // when
        boolean actual = bishop.get().isMovable(Position.from(XAxis.C, YAxis.ONE), Position.from(XAxis.C, YAxis.ONE));

        // then
        assertThat(actual).isFalse();
    }

    @DisplayName("룩은 Y축이 같은 위치로 이동할 수 있다.")
    @Test
    void isMovable_withSameYAxis() {
        // given
        Board board = Board.createInitializedBoard();
        Optional<AbstractPiece> rook = board.find(Position.from(XAxis.A, YAxis.ONE));

        // when
        boolean actual = rook.get().isMovable(Position.from(XAxis.A, YAxis.ONE), Position.from(XAxis.A, YAxis.TWO));

        // then
        assertThat(actual).isTrue();
    }

    @DisplayName("룩은 X축이 같은 위치로 이동할 수 있다.")
    @Test
    void isMovable_withSameXAxis() {
        // given
        Board board = Board.createInitializedBoard();
        Optional<AbstractPiece> rook = board.find(Position.from(XAxis.A, YAxis.ONE));

        // when
        boolean actual = rook.get().isMovable(Position.from(XAxis.A, YAxis.ONE), Position.from(XAxis.B, YAxis.ONE));

        // then
        assertThat(actual).isTrue();
    }

    @DisplayName("룩은 X축과 Y축이 모두 다른 위치로 이동할 수 없다.")
    @Test
    void isNotMovable_withDifferentXAxisDifferentYAxis() {
        // given
        Board board = Board.createInitializedBoard();
        Optional<AbstractPiece> rook = board.find(Position.from(XAxis.A, YAxis.ONE));

        // when
        boolean actual = rook.get().isMovable(Position.from(XAxis.A, YAxis.ONE), Position.from(XAxis.B, YAxis.TWO));

        // then
        assertThat(actual).isFalse();
    }
}