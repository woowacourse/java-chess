package chess.domain.pieces;

import chess.domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class KingTest {

    @ParameterizedTest
    @MethodSource("kingMovement")
    @DisplayName("킹은 상하좌우 한칸, 위 아래 대각선 한칸씩 움직일 수 있다.")
    void move_aroundOneStep(Position source, Position target, boolean result) {
        Type king = new King();
        assertThat(king.isMovable(source, target)).isEqualTo(result);
    }

    private static Stream<Arguments> kingMovement() {
        return Stream.of(
                Arguments.of(
                        Position.of("a1"), Position.of("a2"), true
                ),
                Arguments.of(
                        Position.of("a1"), Position.of("a3"), false
                ),
                Arguments.of(
                        Position.of("c6"), Position.of("b5"), true
                ),
                Arguments.of(
                        Position.of("c6"), Position.of("a4"), false
                )
        );
    }
}
