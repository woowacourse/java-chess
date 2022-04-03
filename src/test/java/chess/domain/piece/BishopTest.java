package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.piece.role.Bishop;
import chess.domain.piece.role.Role;
import chess.domain.position.Position;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class BishopTest {

    @ParameterizedTest
    @MethodSource("bishopRightMovement")
    @DisplayName("비숍은 대각선으로 움직일 수 있다")
    void move_diagonal(Position source, Position target) {
        Role bishop = new Bishop();
        assertThatCode(() -> bishop.checkMovable(source, target))
                .doesNotThrowAnyException();
    }

    private static Stream<Arguments> bishopRightMovement() {
        return Stream.of(
                Arguments.of(Position.of("a1"), Position.of("d4")),
                Arguments.of(Position.of("c6"), Position.of("b5")),
                Arguments.of(Position.of("d8"), Position.of("h4"))
        );
    }

    @ParameterizedTest
    @MethodSource("bishopWrongMovement")
    @DisplayName("비숍이 대각선이 아닌 곳으로 이동하려 할 시, 예외를 발생한다")
    void targetNotOnDiagonal_throwException(Position source, Position target) {
        Role bishop = new Bishop();
        assertThatThrownBy(() -> bishop.checkMovable(source, target))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("비숍");
    }

    private static Stream<Arguments> bishopWrongMovement() {
        return Stream.of(
                Arguments.of(Position.of("c6"), Position.of("f8")),
                Arguments.of(Position.of("d6"), Position.of("a8")),
                Arguments.of(Position.of("e1"), Position.of("e8"))
        );
    }

}
