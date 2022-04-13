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
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class KingMovingStrategyTest {

    @DisplayName("처음과 같은 위치로 이동할 수 없다.")
    @Test
    void isNotMovable_withSamePosition() {
        // given
        Board board = Board.createInitializedBoard();
        Optional<Piece> king = board.find(Position.of(XAxis.E, YAxis.ONE));

        // when
        boolean actual = king.get().isAbleToMove(Position.of(XAxis.E, YAxis.ONE), Position.of(XAxis.E, YAxis.ONE));

        // then
        assertThat(actual).isFalse();
    }

    @DisplayName("X축과 Y축이 모두 다른 위치로 이동할 수 있다.")
    @Test
    void isMovable_withDifferentXAxisDifferentYAxis() {
        // given
        Board board = Board.createInitializedBoard();
        Optional<Piece> king = board.find(Position.of(XAxis.E, YAxis.ONE));

        // when
        boolean actual = king.get().isAbleToMove(Position.of(XAxis.E, YAxis.ONE), Position.of(XAxis.E, YAxis.TWO));

        // then
        assertThat(actual).isTrue();
    }

    @DisplayName("Y축이 같은 위치로 이동할 수 있다.")
    @Test
    void isMovable_withSameYAxis() {
        // given
        Board board = Board.createInitializedBoard();
        Optional<Piece> king = board.find(Position.of(XAxis.E, YAxis.ONE));

        // when
        boolean actual = king.get().isAbleToMove(Position.of(XAxis.E, YAxis.ONE), Position.of(XAxis.E, YAxis.TWO));

        // then
        assertThat(actual).isTrue();
    }

    @DisplayName("X축이 같은 위치로 이동할 수 있다.")
    @Test
    void isMovable_withSameXAxis() {
        // given
        Board board = Board.createInitializedBoard();
        Optional<Piece> king = board.find(Position.of(XAxis.E, YAxis.ONE));

        // when
        boolean actual = king.get().isAbleToMove(Position.of(XAxis.E, YAxis.ONE), Position.of(XAxis.E, YAxis.TWO));

        // then
        assertThat(actual).isTrue();
    }

    @DisplayName("대각선 혹은 수직, 수평이 아닌 방향으로 이동할 수 없다.")
    @Test
    void isNotMovable_notOnDiagonal() {
        // given
        Board board = Board.createInitializedBoard();
        Optional<Piece> king = board.find(Position.of(XAxis.E, YAxis.ONE));

        // when
        boolean actual = king.get()
                .isAbleToMove(Position.of(XAxis.E, YAxis.ONE), Position.of(XAxis.F, YAxis.THREE));

        // then
        assertThat(actual).isFalse();
    }

    @DisplayName("한번에 두칸 이상 이동할 수 없다.")
    @ParameterizedTest
    @CsvSource(value = {"G,THREE", "G,ONE", "C,ONE", "C,THREE", "H,FOUR"})
    void isNotMovable_MoreThanOne(String xAxisName, String yAxisName) {
        // given
        Board board = Board.createInitializedBoard();
        Optional<Piece> king = board.find(Position.of(XAxis.E, YAxis.ONE));

        // when
        boolean actual = king.get().isAbleToMove(Position.of(XAxis.E, YAxis.ONE),
                Position.of(XAxis.valueOf(xAxisName), YAxis.valueOf(yAxisName)));

        // then
        assertThat(actual).isFalse();
    }
}
