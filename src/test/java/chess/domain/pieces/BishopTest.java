package chess.domain.pieces;

import chess.domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class BishopTest {

    @ParameterizedTest
    @MethodSource("bishopDiagonalMovement")
    @DisplayName("비숍은 대각선으로 움직인다.")
    void move_diagonal(Position source, Position target, boolean result) {
        Type bishop = new Bishop();
        assertThat(bishop.isMovable(source, target)).isEqualTo(result);
    }

    private static Stream<Arguments> bishopDiagonalMovement() {
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
