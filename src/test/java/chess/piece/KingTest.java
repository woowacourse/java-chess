package chess.piece;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import chess.Column;
import chess.Movement;
import chess.Position;
import chess.Row;

class KingTest {

    @MethodSource
    @ParameterizedTest
    void 킹은_직선을_한칸_움직일_수_있다(Movement movement, Position target) {
        King king = new King(new Position(Row.TWO, Column.D));

        assertThat(king.move(movement)).isEqualTo(new King(target));
    }

    private static Stream<Arguments> 킹은_직선을_한칸_움직일_수_있다() {
        return Stream.of(
                Arguments.of(Movement.UP, new Position(Row.THREE, Column.D)),
                Arguments.of(Movement.DOWN, new Position(Row.ONE, Column.D)),
                Arguments.of(Movement.LEFT, new Position(Row.TWO, Column.C)),
                Arguments.of(Movement.RIGHT, new Position(Row.TWO, Column.E))
        );
    }

    @MethodSource
    @ParameterizedTest
    void 킹은_대각선을_한칸_움직일_수_있다(Movement movement, Position target) {
        King king = new King(new Position(Row.TWO, Column.D));

        assertThat(king.move(movement)).isEqualTo(new King(target));
    }

    private static Stream<Arguments> 킹은_대각선을_한칸_움직일_수_있다() {
        return Stream.of(
                Arguments.of(Movement.LEFT_UP, new Position(Row.THREE, Column.C)),
                Arguments.of(Movement.RIGHT_UP, new Position(Row.THREE, Column.E)),
                Arguments.of(Movement.LEFT_DOWN, new Position(Row.ONE, Column.C)),
                Arguments.of(Movement.RIGHT_DOWN, new Position(Row.ONE, Column.E))
        );
    }

}
