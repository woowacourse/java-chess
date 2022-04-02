package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.piece.role.Queen;
import chess.domain.piece.role.Role;
import chess.domain.position.Position;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class QueenTest {

    @ParameterizedTest
    @MethodSource("queenRightMovement")
    @DisplayName("퀸은 상하좌우 대각선 이동할 수 있다")
    void move_verticalHorizonTalDiagonal(Position source, Position target) {
        Role queen = new Queen();
        assertThatCode(() -> queen.checkMovable(source, target))
                .doesNotThrowAnyException();
    }

    private static Stream<Arguments> queenRightMovement() {
        return Stream.of(
                Arguments.of(Position.of("a1"), Position.of("a8")),
                Arguments.of(Position.of("d8"), Position.of("b8")),
                Arguments.of(Position.of("a1"), Position.of("d4")),
                Arguments.of(Position.of("c6"), Position.of("b5"))
        );
    }

    @ParameterizedTest
    @MethodSource("queenWrongMovement")
    @DisplayName("퀸이 상하좌우 대각선이 아닌 곳으로 이동하려 할 시, 예외를 발생한다")
    void targetNotOn_VerticalHorizontalDiagonal_throwException(Position source, Position target) {
        Role queen = new Queen();
        assertThatThrownBy(() -> queen.checkMovable(source, target))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("퀸");
    }

    private static Stream<Arguments> queenWrongMovement() {
        return Stream.of(
                Arguments.of(Position.of("a1"), Position.of("b3")),
                Arguments.of(Position.of("b2"), Position.of("g8")),
                Arguments.of(Position.of("f8"), Position.of("c3"))
        );
    }
}
