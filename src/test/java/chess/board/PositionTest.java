package chess.board;

import static org.assertj.core.api.Assertions.assertThat;

import chess.piece.Direction;
import java.util.stream.Stream;
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
    @MethodSource("getDirectionToCase")
    @DisplayName("대상 Position을 받아서 대상 Position으로 향하는 방향을 반환한다.")
    void getDirectionTo_Up(Position position, Position targetPosition, Direction expectedDirection) {
        // when, then
        assertThat(position.getDirectionTo(targetPosition)).isSameAs(expectedDirection);
    }

    static Stream<Arguments> getDirectionToCase() {
        return Stream.of(
                Arguments.of(new Position(File.A, Rank.ONE), new Position(File.A, Rank.FIVE), Direction.UP),
                Arguments.of(new Position(File.A, Rank.ONE), new Position(File.B, Rank.TWO), Direction.UP_RIGHT),
                Arguments.of(new Position(File.A, Rank.ONE), new Position(File.B, Rank.ONE), Direction.RIGHT),
                Arguments.of(new Position(File.B, Rank.TWO), new Position(File.C, Rank.ONE), Direction.DOWN_RIGHT),
                Arguments.of(new Position(File.B, Rank.TWO), new Position(File.B, Rank.ONE), Direction.DOWN),
                Arguments.of(new Position(File.B, Rank.TWO), new Position(File.A, Rank.ONE), Direction.DOWN_LEFT),
                Arguments.of(new Position(File.B, Rank.TWO), new Position(File.A, Rank.TWO), Direction.LEFT),
                Arguments.of(new Position(File.B, Rank.TWO), new Position(File.A, Rank.THREE), Direction.UP_LEFT)
        );
    }

    @Test
    @DisplayName("대상 Position을 받아서 현재 Position과의 Slope를 구한다. - 0.5")
    void getSlope_Zero_Five() {
        // given
        final Position position = new Position(File.C, Rank.TWO);
        final Position targetPosition = new Position(File.D, Rank.FOUR);

        // when, then
        assertThat(position.getSlope(targetPosition)).isEqualTo(0.5);
    }

    @Test
    @DisplayName("대상 Position을 받아서 현재 Position과의 Slope를 구한다. - 2.0")
    void getSlope_Two() {
        // given
        final Position position = new Position(File.D, Rank.FOUR);
        final Position targetPosition = new Position(File.B, Rank.THREE);

        // when, then
        assertThat(position.getSlope(targetPosition)).isEqualTo(2.0);
    }

    @ParameterizedTest
    @MethodSource("getMoveCountCase")
    @DisplayName("이동할 횟수를 반환한다.")
    void getMoveCount(Position sourcePosition,
                      Position targetPosition,
                      Direction direction,
                      int expectedMoveCount) {
        // when
        int moveCount = sourcePosition.getMoveCount(targetPosition, direction);

        // then
        assertThat(moveCount).isEqualTo(expectedMoveCount);
    }

    static Stream<Arguments> getMoveCountCase() {
        return Stream.of(
                Arguments.of(new Position(File.D, Rank.FOUR), new Position(File.F, Rank.FOUR), Direction.RIGHT, 2),
                Arguments.of(new Position(File.D, Rank.FOUR), new Position(File.D, Rank.TWO), Direction.DOWN, 2),
                Arguments.of(new Position(File.D, Rank.FOUR), new Position(File.F, Rank.SIX), Direction.UP_RIGHT, 2)
        );
    }

    @ParameterizedTest
    @MethodSource("getNextPositionCase")
    @DisplayName("입력받은 방향에 따라 다음 Position을 반환한다. - 수평")
    void getNextPosition(Position position, Direction direction, Position expectedPosition) {
        // when
        final Position nextPosition = position.getNextPosition(direction);

        // then
        assertThat(nextPosition).isEqualTo(expectedPosition);
    }

    static Stream<Arguments> getNextPositionCase() {
        return Stream.of(
                Arguments.of(new Position(File.D, Rank.FOUR), Direction.RIGHT, new Position(File.E, Rank.FOUR)),
                Arguments.of(new Position(File.D, Rank.FOUR), Direction.DOWN, new Position(File.D, Rank.THREE)),
                Arguments.of(new Position(File.D, Rank.FOUR), Direction.UP_RIGHT, new Position(File.E, Rank.FIVE))
        );
    }
}

