package domain.piece;

import domain.board.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class EmptyTest {

    @ParameterizedTest(name = "source : {0}, target : {1}")
    @MethodSource("canMoveDirection")
    @DisplayName("빈 칸은 어느 방향으로도 이동 불가")
    void canMove(Position source, Position target) {
        Empty empty = new Empty(Color.EMPTY);
        assertThat(empty.canMove(source, target)).isFalse();
    }

    private static Stream<Arguments> canMoveDirection() {
        return Stream.of(
                Arguments.of(PositionArgument.FROM, PositionArgument.UP),
                Arguments.of(PositionArgument.FROM, PositionArgument.DOWN),
                Arguments.of(PositionArgument.FROM, PositionArgument.LEFT),
                Arguments.of(PositionArgument.FROM, PositionArgument.RIGHT),
                Arguments.of(PositionArgument.FROM, PositionArgument.UP_LEFT),
                Arguments.of(PositionArgument.FROM, PositionArgument.UP_RIGHT),
                Arguments.of(PositionArgument.FROM, PositionArgument.DOWN_LEFT),
                Arguments.of(PositionArgument.FROM, PositionArgument.DOWN_RIGHT)
        );
    }
}
