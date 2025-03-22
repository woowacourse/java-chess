package chess.piece;

import static chess.Movement.DOWN_DOWN_LEFT;
import static chess.Movement.DOWN_DOWN_RIGHT;
import static chess.Movement.LEFT_LEFT_DOWN;
import static chess.Movement.LEFT_LEFT_UP;
import static chess.Movement.RIGHT_RIGHT_DOWN;
import static chess.Movement.RIGHT_RIGHT_UP;
import static chess.Movement.UP_UP;
import static chess.Movement.UP_UP_LEFT;
import static chess.Movement.UP_UP_RIGHT;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import chess.Column;
import chess.Fixtures;
import chess.Movement;
import chess.Position;
import chess.Row;

class KnightTest {

    @MethodSource
    @ParameterizedTest
    void 나이트는_한_방향_두_칸_그와_90도를_이루는_방향_한_칸을_움직일_수_있다(Movement movement, Position position) {
        Knight knight = new Knight(new Position(Row.THREE, Column.D));

        assertThat(knight.move(movement)).isEqualTo(new Knight(position));
    }

    private static Stream<Arguments> 나이트는_한_방향_두_칸_그와_90도를_이루는_방향_한_칸을_움직일_수_있다() {
        return Stream.of(
                Arguments.of(LEFT_LEFT_UP, new Position(Row.FOUR, Column.B)),
                Arguments.of(LEFT_LEFT_DOWN, new Position(Row.TWO, Column.B)),
                Arguments.of(RIGHT_RIGHT_UP, new Position(Row.FOUR, Column.F)),
                Arguments.of(RIGHT_RIGHT_DOWN, new Position(Row.TWO, Column.F)),
                Arguments.of(UP_UP_LEFT, new Position(Row.FIVE, Column.C)),
                Arguments.of(UP_UP_RIGHT, new Position(Row.FIVE, Column.E)),
                Arguments.of(DOWN_DOWN_LEFT, new Position(Row.ONE, Column.C)),
                Arguments.of(DOWN_DOWN_RIGHT, new Position(Row.ONE, Column.E))
        );
    }

    @MethodSource
    @ParameterizedTest
    void 나이트가_움직일_수_없다(Position position, Movement movement) {
        Knight knight = new Knight(position);

        assertThatThrownBy(() -> knight.move(movement))
                .isInstanceOf(IllegalArgumentException.class);
    }

    private static Stream<Arguments> 나이트가_움직일_수_없다() {
        return Stream.of(
                Arguments.of(Fixtures.F8, LEFT_LEFT_UP),
                Arguments.of(Fixtures.F1, LEFT_LEFT_DOWN),
                Arguments.of(Fixtures.G8, RIGHT_RIGHT_UP),
                Arguments.of(Fixtures.G8, RIGHT_RIGHT_DOWN),
                Arguments.of(Fixtures.A6, UP_UP_LEFT),
                Arguments.of(Fixtures.H6, UP_UP_RIGHT),
                Arguments.of(Fixtures.A3, DOWN_DOWN_LEFT),
                Arguments.of(Fixtures.A2, DOWN_DOWN_RIGHT),
                Arguments.of(Fixtures.A2, UP_UP)
        );
    }

}
