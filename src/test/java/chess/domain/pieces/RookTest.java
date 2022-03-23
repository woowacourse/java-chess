package chess.domain.pieces;

import chess.domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class RookTest {

    @ParameterizedTest
    @MethodSource("rookVerticalMovement")
    @DisplayName("룩은 상하 직진한다")
    void move_goVertical(Position source, Position target, boolean result) {
        Rook rook = new Rook();
        assertThat(rook.isMovable(source, target)).isEqualTo(result);
    }

    private static Stream<Arguments> rookVerticalMovement() {
        return Stream.of(
                Arguments.of(
                        Position.of("a1"), Position.of("a8"), true
                ),
                Arguments.of(
                        Position.of("a1"), Position.of("b2"), false
                ),
                Arguments.of(
                        Position.of("a8"), Position.of("a7"), true
                ),
                Arguments.of(
                        Position.of("a8"), Position.of("b7"), false
                )
        );
    }

    @ParameterizedTest
    @MethodSource("rookHorizontalMovement")
    @DisplayName("룩은 좌우 직진한다")
    void move_goHorizontal(Position source, Position target, boolean result) {
        Rook rook = new Rook();
        assertThat(rook.isMovable(source, target)).isEqualTo(result);
    }

    private static Stream<Arguments> rookHorizontalMovement() {
        return Stream.of(
                Arguments.of(
                        Position.of("a7"), Position.of("c7"), true
                ),
                Arguments.of(
                        Position.of("c1"), Position.of("d7"), false
                ),
                Arguments.of(
                        Position.of("d8"), Position.of("b8"), true
                ),
                Arguments.of(
                        Position.of("d7"), Position.of("c6"), false
                )
        );
    }
}
