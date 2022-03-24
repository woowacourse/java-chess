package chess.domain.pieces;

import chess.domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class PawnTest {

    @ParameterizedTest
    @MethodSource("pawnMovement")
    @DisplayName("폰은 수직으로 한 칸 이동한다")
    void move_verticalOneStep(Position source, Position target, boolean result) {
        Type pawn = new Pawn();
        assertThat(pawn.isMovable(source, target)).isEqualTo(result);
    }

    private static Stream<Arguments> pawnMovement() {
        return Stream.of(
                Arguments.of(
                        Position.of("a1"), Position.of("a2"), true
                ),
                Arguments.of(
                        Position.of("a1"), Position.of("b1"), false
                ),
                Arguments.of(
                        Position.of("e5"), Position.of("e4"), true
                )
        );
    }

    @Test
    @DisplayName("폰은 직진으로 2칸 움직일 수 있다")
    void move_first_allowVerticalTwoStep() {
        Type pawn = new Pawn();
        assertThat(pawn.isMovable(Position.of("a2"), Position.of("a4"))).isTrue();
    }

    @ParameterizedTest
    @MethodSource("pawnDiagonalMovement")
    @DisplayName("폰은 대각으로 1칸 움직일 수 있다")
    void move_diagonalOneStep(Position source, Position target, boolean result) {
        Type pawn = new Pawn();
        assertThat(pawn.isMovable(source, target)).isEqualTo(result);
    }

    private static Stream<Arguments> pawnDiagonalMovement() {
        return Stream.of(
                Arguments.of(
                        Position.of("a1"), Position.of("b2"), true
                ),
                Arguments.of(
                        Position.of("a1"), Position.of("c3"), false
                ),
                Arguments.of(
                        Position.of("e5"), Position.of("d4"), true
                ),
                Arguments.of(
                        Position.of("e5"), Position.of("c3"), false
                )
        );
    }
}
