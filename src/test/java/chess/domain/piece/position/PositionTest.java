package chess.domain.piece.position;

import java.util.stream.Stream;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class PositionTest {

    @ParameterizedTest(name = "{index}: {1}")
    @MethodSource("invalidParameters")
    @DisplayName("막혀있는 곳으로 이동")
    void isBlocked(Position position, Direction direction) {
        Assertions.assertThat(position.isBlocked(direction)).isTrue();
    }

    private static Stream<Arguments> invalidParameters() {
        return Stream.of(
            Arguments.of(Position.of(File.A, Rank.ONE), Direction.UP_UP_LEFT, ""),
            Arguments.of(Position.of(File.A, Rank.ONE), Direction.UP_LEFT_LEFT, ""),
            Arguments.of(Position.of(File.A, Rank.ONE), Direction.UP_LEFT, ""),
            Arguments.of(Position.of(File.A, Rank.ONE), Direction.LEFT, ""),
            Arguments.of(Position.of(File.A, Rank.ONE), Direction.DOWN_LEFT_LEFT, ""),
            Arguments.of(Position.of(File.A, Rank.ONE), Direction.DOWN_LEFT, ""),
            Arguments.of(Position.of(File.A, Rank.ONE), Direction.DOWN, ""),
            Arguments.of(Position.of(File.A, Rank.ONE), Direction.DOWN_RIGHT, ""),
            Arguments.of(Position.of(File.A, Rank.ONE), Direction.DOWN_RIGHT_RIGHT, ""),
            Arguments.of(Position.of(File.A, Rank.ONE), Direction.DOWN_DOWN_LEFT, ""),
            Arguments.of(Position.of(File.A, Rank.ONE), Direction.DOWN_DOWN_RIGHT, ""),

            Arguments.of(Position.of(File.H, Rank.EIGHT), Direction.UP_UP_LEFT, ""),
            Arguments.of(Position.of(File.H, Rank.EIGHT), Direction.UP_UP_RIGHT, ""),
            Arguments.of(Position.of(File.H, Rank.EIGHT), Direction.UP_LEFT_LEFT, ""),
            Arguments.of(Position.of(File.H, Rank.EIGHT), Direction.UP_LEFT, ""),
            Arguments.of(Position.of(File.H, Rank.EIGHT), Direction.UP, ""),
            Arguments.of(Position.of(File.H, Rank.EIGHT), Direction.UP_RIGHT, ""),
            Arguments.of(Position.of(File.H, Rank.EIGHT), Direction.UP_RIGHT_RIGHT, ""),
            Arguments.of(Position.of(File.H, Rank.EIGHT), Direction.RIGHT, ""),
            Arguments.of(Position.of(File.H, Rank.EIGHT), Direction.DOWN_RIGHT, ""),
            Arguments.of(Position.of(File.H, Rank.EIGHT), Direction.DOWN_RIGHT_RIGHT, ""),
            Arguments.of(Position.of(File.H, Rank.EIGHT), Direction.DOWN_DOWN_RIGHT, "")
        );
    }
}