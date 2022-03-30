package chess.domain.pieces;

import chess.domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class KnightTest {

    @ParameterizedTest
    @MethodSource("knightMovement")
    @DisplayName("나이트는 상하좌우 두칸 직진 후 좌우로 이동한다")
    void move_straightTwoStepsAndHorizontalOneStep(Position source, Position target, boolean result) {
        Role knight = new Knight();
        assertThat(knight.isMovable(source, target)).isEqualTo(result);
    }

    private static Stream<Arguments> knightMovement() {
        return Stream.of(
                Arguments.of(
                        Position.of("a1"), Position.of("b3"), true
                ),
                Arguments.of(
                        Position.of("a1"), Position.of("a4"), false
                ),
                Arguments.of(
                        Position.of("b3"), Position.of("c1"), true
                ),
                Arguments.of(
                        Position.of("b3"), Position.of("c4"), false
                )
        );
    }
}
