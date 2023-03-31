package chess.domain.piece;

import chess.domain.side.Color;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static chess.domain.piece.Direction.*;
import static chess.domain.side.Color.BLACK;
import static chess.domain.side.Color.WHITE;
import static org.assertj.core.api.Assertions.assertThat;

class KnightTest {
    static Stream<Arguments> canMoveDummy() {
        return Stream.of(
                Arguments.of(NORTH_NORTH_WEST, 1),
                Arguments.of(NORTH_WEST_WEST, 1),
                Arguments.of(NORTH_EAST_EAST, 1),
                Arguments.of(NORTH_NORTH_EAST, 1),
                Arguments.of(SOUTH_SOUTH_EAST, 1),
                Arguments.of(SOUTH_EAST_EAST, 1),
                Arguments.of(SOUTH_SOUTH_WEST, 1),
                Arguments.of(SOUTH_WEST_WEST, 1)
        );
    }

    static Stream<Arguments> canNotMoveDummy() {
        return Stream.of(
                Arguments.of(NORTH_EAST, 1),
                Arguments.of(NORTH_WEST, 1),
                Arguments.of(SOUTH_EAST, 1),
                Arguments.of(SOUTH_WEST, 1),
                Arguments.of(NORTH, 1),
                Arguments.of(EAST, 1),
                Arguments.of(SOUTH, 1),
                Arguments.of(WEST, 1)
        );
    }

    @ParameterizedTest
    @MethodSource("canMoveDummy")
    @DisplayName("이동할 수 있는지 확인한다.")
    void isMovable(final Direction direction, final int distance) {
        // when
        Knight knight = new Knight(Color.BLACK, Role.KNIGHT);

        // expected
        assertThat(knight.canMove(direction, distance)).isTrue();
    }

    @ParameterizedTest
    @MethodSource("canNotMoveDummy")
    @DisplayName("이동할 수 없는지 확인한다.")
    void isUnmovable(final Direction direction, final int distance) {
        // when
        Knight knight = new Knight(Color.BLACK, Role.KNIGHT);

        // expected
        assertThat(knight.canMove(direction, distance)).isFalse();
    }

    @ParameterizedTest
    @MethodSource("canMoveDummy")
    @DisplayName("공격할 수 있는지 확인한다.")
    void canAttack(final Direction direction, final int distance) {
        // when
        Knight knight = new Knight(Color.BLACK, Role.KNIGHT);
        Pawn opponentPiece = new Pawn(WHITE, Role.PAWN);

        // expected
        assertThat(knight.canAttack(direction, distance, opponentPiece)).isTrue();
    }

    @ParameterizedTest
    @MethodSource("canNotMoveDummy")
    @DisplayName("공격할 수 없는지 확인한다.")
    void canNotAttack(final Direction direction, final int distance) {
        // when
        Knight knight = new Knight(Color.BLACK, Role.KNIGHT);
        Pawn opponentPiece = new Pawn(BLACK, Role.PAWN);

        // expected
        assertThat(knight.canAttack(direction, distance, opponentPiece)).isFalse();
    }
}
