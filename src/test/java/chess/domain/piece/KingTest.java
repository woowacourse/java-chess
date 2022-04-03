package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.piece.role.King;
import chess.domain.piece.role.Role;
import chess.domain.position.Position;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class KingTest {

    @ParameterizedTest
    @MethodSource("kingRightMovement")
    @DisplayName("킹은 상하좌우 한칸, 위 아래 대각선 한칸씩 움직일 수 있다")
    void move_oneBlock(Position source, Position target) {
        Role king = new King();
        assertThatCode(() -> king.checkMovable(source, target))
                .doesNotThrowAnyException();
    }

    private static Stream<Arguments> kingRightMovement() {
        return Stream.of(
                Arguments.of(Position.of("a1"), Position.of("a2")),
                Arguments.of(Position.of("c5"), Position.of("d4")),
                Arguments.of(Position.of("d8"), Position.of("c7"))
        );
    }

    @ParameterizedTest
    @MethodSource("kingWrongMovement")
    @DisplayName("킹이 방향 관계없이 두 칸 이상 이동하려 할 시, 예외를 발생한다")
    void targetNotAdjacent_throwException(Position source, Position target) {
        Role king = new King();
        assertThatThrownBy(() -> king.checkMovable(source, target))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("킹");
    }

    private static Stream<Arguments> kingWrongMovement() {
        return Stream.of(
                Arguments.of(Position.of("a1"), Position.of("a3")),
                Arguments.of(Position.of("c5"), Position.of("a3")),
                Arguments.of(Position.of("d6"), Position.of("d4"))
        );
    }
}

