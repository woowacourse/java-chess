package chess.domain.pieces;

import chess.domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;


import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class QueenTest {

    @ParameterizedTest
    @MethodSource("queenVerticalMovement")
    @DisplayName("퀸은 상하 이동을 할 수 있다")
    void move_vertical(Position source, Position target, boolean result) {
        Type queen = new Queen();
        assertThat(queen.isMovable(source, target)).isEqualTo(result);
    }

    private static Stream<Arguments> queenVerticalMovement() {
        return Stream.of(
                Arguments.of(
                        Position.of("a1"), Position.of("a8"), true
                ),
                Arguments.of(
                        Position.of("a8"), Position.of("a7"), true
                )
        );
    }

    @ParameterizedTest
    @MethodSource("queenHorizontalMovement")
    @DisplayName("퀸은 좌우 이동을 할 수 있다")
    void move_horizontal(Position source, Position target, boolean result) {
        Type queen = new Queen();
        assertThat(queen.isMovable(source, target)).isEqualTo(result);
    }

    private static Stream<Arguments> queenHorizontalMovement() {
        return Stream.of(
                Arguments.of(
                        Position.of("a7"), Position.of("c7"), true
                ),
                Arguments.of(
                        Position.of("d8"), Position.of("b8"), true
                )
        );
    }

    @ParameterizedTest
    @MethodSource("queenDiagonalMovement")
    @DisplayName("퀸은 대각선으로 이동할 수 있다")
    void move_diagonal(Position source, Position target, boolean result) {
        Type queen = new Queen();
        assertThat(queen.isMovable(source, target)).isEqualTo(result);
    }

    private static Stream<Arguments> queenDiagonalMovement() {
        return Stream.of(
                Arguments.of(
                        Position.of("a1"), Position.of("d4"), true
                ),
                Arguments.of(
                        Position.of("c6"), Position.of("f8"), false
                ),
                Arguments.of(
                        Position.of("c6"), Position.of("b5"), true
                ),
                Arguments.of(
                        Position.of("d6"), Position.of("a8"), false
                )
        );
    }
}
