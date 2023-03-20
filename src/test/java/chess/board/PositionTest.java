package chess.board;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Stream;

import chess.piece.Direction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class PositionTest {

    @Test
    @DisplayName("File과 Rank를 받아서 Position을 생성한다.")
    void generatePosition() {
        // when, then
        Assertions.assertDoesNotThrow(() -> new Position(File.A, Rank.ONE));
    }

    @ParameterizedTest
    @MethodSource("getDirectionToTestCases")
    @DisplayName("대상 Position을 받아서 대상 Position으로 향하는 방향을 반환한다.")
    void getDirectionTo(Position position, Position targetPosition, Direction expectedDirection) {
        // when, then
        assertThat(position.getDirectionTo(targetPosition)).isSameAs(expectedDirection);
    }

    static Stream<Arguments> getDirectionToTestCases() {
        return Stream.of(
                Arguments.arguments(new Position(File.A, Rank.ONE), new Position(File.A, Rank.FIVE), Direction.UP),
                Arguments.arguments(new Position(File.B, Rank.TWO), new Position(File.B, Rank.THREE), Direction.UP),
                Arguments.arguments(new Position(File.C, Rank.ONE), new Position(File.E, Rank.THREE), Direction.UP_RIGHT),
                Arguments.arguments(new Position(File.D, Rank.ONE), new Position(File.E, Rank.TWO), Direction.UP_RIGHT),
                Arguments.arguments(new Position(File.A, Rank.ONE), new Position(File.G, Rank.ONE), Direction.RIGHT),
                Arguments.arguments(new Position(File.A, Rank.EIGHT), new Position(File.H, Rank.EIGHT), Direction.RIGHT),
                Arguments.arguments(new Position(File.C, Rank.EIGHT), new Position(File.D, Rank.SEVEN), Direction.DOWN_RIGHT),
                Arguments.arguments(new Position(File.D, Rank.EIGHT), new Position(File.E, Rank.SEVEN), Direction.DOWN_RIGHT),
                Arguments.arguments(new Position(File.A, Rank.SEVEN), new Position(File.A, Rank.FIVE), Direction.DOWN),
                Arguments.arguments(new Position(File.H, Rank.EIGHT), new Position(File.H, Rank.FOUR), Direction.DOWN),
                Arguments.arguments(new Position(File.C, Rank.EIGHT), new Position(File.A, Rank.SIX), Direction.DOWN_LEFT),
                Arguments.arguments(new Position(File.D, Rank.EIGHT), new Position(File.C, Rank.SEVEN), Direction.DOWN_LEFT)
        );
    }

    @ParameterizedTest
    @MethodSource("getSlopeTestCases")
    @DisplayName("대상 Position을 받아서 현재 Position과의 Slope를 구한다.")
    void getSlope(Position position, Position targetPosition, double expectedSlope) {
        // when, then
        assertThat(position.getSlope(targetPosition)).isEqualTo(expectedSlope);
    }

    static Stream<Arguments> getSlopeTestCases() {
        return Stream.of(
            Arguments.arguments(new Position(File.D, Rank.FOUR), new Position(File.B, Rank.THREE), 2.0),
            Arguments.arguments(new Position(File.D, Rank.FOUR), new Position(File.F, Rank.FIVE), 2.0),
            Arguments.arguments(new Position(File.C, Rank.TWO), new Position(File.D, Rank.FOUR), 0.5),
            Arguments.arguments(new Position(File.C, Rank.TWO), new Position(File.B, Rank.FOUR), 0.5),
            Arguments.arguments(new Position(File.E, Rank.TWO), new Position(File.E, Rank.FOUR), 0),
            Arguments.arguments(new Position(File.C, Rank.TWO), new Position(File.D, Rank.TWO), 0)
        );
    }

    @ParameterizedTest
    @MethodSource("getMoveCountTestCases")
    @DisplayName("이동할 횟수를 반환한다.")
    void getMoveCount(Position sourcePosition, Position targetPosition, Direction direction, int expectedMoveCount) {
        // when, then
        assertThat(sourcePosition.getMoveCount(targetPosition, direction)).isEqualTo(expectedMoveCount);
    }

    static Stream<Arguments> getMoveCountTestCases() {
        return Stream.of(
                Arguments.arguments(new Position(File.D, Rank.FOUR), new Position(File.F, Rank.FOUR), Direction.RIGHT, 2),
                Arguments.arguments(new Position(File.D, Rank.FOUR), new Position(File.A, Rank.FOUR), Direction.LEFT, 3),
                Arguments.arguments(new Position(File.D, Rank.FOUR), new Position(File.D, Rank.THREE), Direction.DOWN, 1),
                Arguments.arguments(new Position(File.D, Rank.FOUR), new Position(File.D, Rank.EIGHT), Direction.UP, 4),
                Arguments.arguments(new Position(File.D, Rank.FOUR), new Position(File.F, Rank.SIX), Direction.UP_RIGHT, 2),
                Arguments.arguments(new Position(File.D, Rank.FOUR), new Position(File.A, Rank.SEVEN), Direction.UP_LEFT, 3),
                Arguments.arguments(new Position(File.D, Rank.FOUR), new Position(File.E, Rank.THREE), Direction.DOWN_RIGHT, 1),
                Arguments.arguments(new Position(File.D, Rank.FOUR), new Position(File.C, Rank.THREE), Direction.DOWN_LEFT, 1)
        );
    }


    @ParameterizedTest
    @MethodSource("getNextPositionTestCases")
    @DisplayName("입력받은 방향에 따라 다음 Position을 반환한다. - 수평")
    void getNextPosition(Position position, Direction direction, Position expectedPosition) {
        // when, then
        assertThat(position.getNextPosition(direction)).isEqualTo(expectedPosition);
    }

    static Stream<Arguments> getNextPositionTestCases() {
        return Stream.of(
                Arguments.arguments(new Position(File.D, Rank.FOUR), Direction.RIGHT, new Position(File.E, Rank.FOUR)),
                Arguments.arguments(new Position(File.D, Rank.FOUR), Direction.LEFT, new Position(File.C, Rank.FOUR)),
                Arguments.arguments(new Position(File.D, Rank.FOUR), Direction.UP, new Position(File.D, Rank.FIVE)),
                Arguments.arguments(new Position(File.D, Rank.FOUR), Direction.DOWN, new Position(File.D, Rank.THREE)),
                Arguments.arguments(new Position(File.D, Rank.FOUR), Direction.UP_RIGHT, new Position(File.E, Rank.FIVE)),
                Arguments.arguments(new Position(File.D, Rank.FOUR), Direction.UP_LEFT, new Position(File.C, Rank.FIVE)),
                Arguments.arguments(new Position(File.D, Rank.FOUR), Direction.DOWN_RIGHT, new Position(File.E, Rank.THREE)),
                Arguments.arguments(new Position(File.D, Rank.FOUR), Direction.DOWN_LEFT, new Position(File.C, Rank.THREE))
        );
    }
}

