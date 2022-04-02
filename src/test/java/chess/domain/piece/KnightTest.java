package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.piece.role.Knight;
import chess.domain.piece.role.Role;
import chess.domain.position.Position;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class KnightTest {

    @ParameterizedTest
    @MethodSource("KnightRightMovement")
    @DisplayName("나이트는 상하좌우 한 칸 직진 후, 대각 한 칸 이동한다")
    void move_crossOneStepThenDiagonal(Position source, Position target) {
        Role knight = new Knight();
        assertThatCode(() -> knight.checkMovable(source, target))
                .doesNotThrowAnyException();
    }

    private static Stream<Arguments> KnightRightMovement() {
        return Stream.of(
                Arguments.of(Position.of("a1"), Position.of("b3")),
                Arguments.of(Position.of("b3"), Position.of("c1"))
        );
    }

    @ParameterizedTest
    @MethodSource("knightWrongMovement")
    @DisplayName("나이트의 목적지가 상하좌우 한 칸 직진 후, 대각 한 칸 상에 있지 않을 시 예외를 발생한다.")
    void targetNotCrossOneStepThenDiagonal_throwException(Position source, Position target) {
        Role knight = new Knight();
        assertThatThrownBy(() -> knight.checkMovable(source, target))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("나이트");
    }

    private static Stream<Arguments> knightWrongMovement() {
        return Stream.of(
                Arguments.of(Position.of("a1"), Position.of("a4")),
                Arguments.of(Position.of("b3"), Position.of("c4"))
        );
    }
}
