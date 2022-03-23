package chess.domain.pieces;

import chess.domain.position.Position;
import org.junit.jupiter.api.DisplayName;
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
                ),
                Arguments.of(
                        Position.of("a1"), Position.of("a3"), false
                )
        );
    }
}
