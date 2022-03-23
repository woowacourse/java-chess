package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.board.Board;
import chess.domain.position.Position;
import chess.domain.position.XAxis;
import chess.domain.position.YAxis;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BishopTest {
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

    @DisplayName("비숍은 X축과 Y축이 모두 다른 위치로 이동할 수 있다.")
    @Test
    void isMovable_withDifferentXAxisDifferentYAxis() {
        // given
        Board board = Board.createInitializedBoard();
        Optional<AbstractPiece> bishop = board.find(Position.from(XAxis.C, YAxis.ONE));

        // when
        boolean actual = bishop.get().isMovable(Position.from(XAxis.C, YAxis.ONE), Position.from(XAxis.D, YAxis.TWO));

        // then
        assertThat(actual).isTrue();
    }

    @DisplayName("비숍은 X축이 같은 위치로 이동할 수 없다.")
    @Test
    void isNotMovable_withSameXAxis() {
        // given
        Board board = Board.createInitializedBoard();
        Optional<AbstractPiece> bishop = board.find(Position.from(XAxis.C, YAxis.ONE));

        // when
        boolean actual = bishop.get().isMovable(Position.from(XAxis.C, YAxis.ONE), Position.from(XAxis.D, YAxis.ONE));

        // then
        assertThat(actual).isFalse();
    }

    @DisplayName("비숍은 Y축이 같은 위치로 이동할 수 없다.")
    @Test
    void isNotMovable_withSameYAxis() {
        // given
        Board board = Board.createInitializedBoard();
        Optional<AbstractPiece> bishop = board.find(Position.from(XAxis.C, YAxis.ONE));

        // when
        boolean actual = bishop.get().isMovable(Position.from(XAxis.C, YAxis.ONE), Position.from(XAxis.C, YAxis.TWO));

        // then
        assertThat(actual).isFalse();
    }

    @DisplayName("비숍은 대각선이 아닌 위치로 이동할 수 없다.")
    @Test
    void isNotMovable_notOnDiagonal() {
        // given
        Board board = Board.createInitializedBoard();
        Optional<AbstractPiece> bishop = board.find(Position.from(XAxis.C, YAxis.ONE));

        // when
        boolean actual = bishop.get().isMovable(Position.from(XAxis.C, YAxis.ONE), Position.from(XAxis.D, YAxis.THREE));

        // then
        assertThat(actual).isFalse();
    }
}