package chess.domain.piece;

import static org.assertj.core.api.Assertions.*;

import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import chess.domain.board.Column;
import chess.domain.board.Position;
import chess.domain.board.Row;

public class PieceTest {
    @DisplayName("체스 말 move test")
    @ParameterizedTest(name = "{3}")
    @MethodSource("movableParams")
    void movable(Position start, Position end, boolean expected, String message) {
        Piece queen = Piece.of(Type.QUEEN, Side.BLACK);
        assertThat(queen.isMovable(start, end)).isEqualTo(expected);
    }

    static Stream<Arguments> movableParams() {
        return Stream.of(
            Arguments.of(Position.of(Row.FOUR, Column.B), Position.of(Row.FOUR, Column.B), false, "시작과 끝이 같은 경우 false"),
            Arguments.of(Position.of(Row.FOUR, Column.C), Position.of(Row.FOUR, Column.B), true, "시작과 끝이 다른 경우 true")
        );
    }
}
