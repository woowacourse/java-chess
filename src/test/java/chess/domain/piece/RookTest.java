package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.piece.role.Role;
import chess.domain.piece.role.Rook;
import chess.domain.position.Position;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class RookTest {

    @ParameterizedTest
    @MethodSource("rookRightMovement")
    @DisplayName("룩은 수직, 수평 직진한다")
    void move_verticalHorizonTal(Position source, Position target) {
        Role rook = new Rook();
        assertThatCode(() -> rook.checkMovable(source, target))
                .doesNotThrowAnyException();
    }

    private static Stream<Arguments> rookRightMovement() {
        return Stream.of(
                Arguments.of(Position.of("a1"), Position.of("a8")),
                Arguments.of(Position.of("d8"), Position.of("b8")),
                Arguments.of(Position.of("c4"), Position.of("h4")),
                Arguments.of(Position.of("g7"), Position.of("g2"))
        );
    }

    @ParameterizedTest
    @MethodSource("rookWrongMovement")
    @DisplayName("룩이 수직, 수평이 아닌 곳으로 이동하려 할 시, 예외를 발생한다")
    void targetNotOn_VerticalHorizontal_throwException(Position source, Position target) {
        Role rook = new Rook();
        assertThatThrownBy(() -> rook.checkMovable(source, target))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("룩");
    }

    private static Stream<Arguments> rookWrongMovement() {
        return Stream.of(
                Arguments.of(Position.of("a1"), Position.of("e5")),
                Arguments.of(Position.of("h8"), Position.of("g1")),
                Arguments.of(Position.of("f5"), Position.of("e4"))
        );
    }
}
