package chess.domain;

import chess.domain.piece.Direction;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class PositionTest {

    @ParameterizedTest
    @MethodSource("getMovementResult")
    @DisplayName("방향에 맞게 이동한다.")
    void moveTowardDirection(Position sourcePosition, Direction direction, Position expectedTargetPosition) {
        Position actualTargetPosition = sourcePosition.moveTowardDirection(direction);

        assertThat(actualTargetPosition).isEqualTo(expectedTargetPosition);
    }

    static Stream<Arguments> getMovementResult() {
        return Stream.of(
                Arguments.of(Position.of(File.D, Rank.FIVE), Direction.UP, Position.of(File.D, Rank.SIX)),
                Arguments.of(Position.of(File.D, Rank.FIVE), Direction.DOWN, Position.of(File.D, Rank.FOUR)),
                Arguments.of(Position.of(File.D, Rank.FIVE), Direction.LEFT, Position.of(File.C, Rank.FIVE)),
                Arguments.of(Position.of(File.D, Rank.FIVE), Direction.RIGHT, Position.of(File.E, Rank.FIVE)),
                Arguments.of(Position.of(File.D, Rank.FIVE), Direction.LEFT_UP, Position.of(File.C, Rank.SIX)),
                Arguments.of(Position.of(File.D, Rank.FIVE), Direction.LEFT_DOWN, Position.of(File.C, Rank.FOUR)),
                Arguments.of(Position.of(File.D, Rank.FIVE), Direction.RIGHT_UP, Position.of(File.E, Rank.SIX)),
                Arguments.of(Position.of(File.D, Rank.FIVE), Direction.RIGHT_DOWN, Position.of(File.E, Rank.FOUR)),
                Arguments.of(Position.of(File.D, Rank.FIVE), Direction.KNIGHT_LEFT_UP, Position.of(File.B, Rank.SIX)),
                Arguments.of(Position.of(File.D, Rank.FIVE), Direction.KNIGHT_LEFT_DOWN, Position.of(File.B, Rank.FOUR)),
                Arguments.of(Position.of(File.D, Rank.FIVE), Direction.KNIGHT_RIGHT_UP, Position.of(File.F, Rank.SIX)),
                Arguments.of(Position.of(File.D, Rank.FIVE), Direction.KNIGHT_RIGHT_DOWN, Position.of(File.F, Rank.FOUR)),
                Arguments.of(Position.of(File.D, Rank.FIVE), Direction.KNIGHT_UP_LEFT, Position.of(File.C, Rank.SEVEN)),
                Arguments.of(Position.of(File.D, Rank.FIVE), Direction.KNIGHT_UP_RIGHT, Position.of(File.E, Rank.SEVEN)),
                Arguments.of(Position.of(File.D, Rank.FIVE), Direction.KNIGHT_DOWN_LEFT, Position.of(File.C, Rank.THREE)),
                Arguments.of(Position.of(File.D, Rank.FIVE), Direction.KNIGHT_DOWN_RIGHT, Position.of(File.E, Rank.THREE))
        );
    }
}
