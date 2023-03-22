package chess.domain.move;

import static chess.domain.move.Direction.LEFT;
import static chess.domain.move.Direction.RIGHT;
import static chess.domain.move.Direction.UP;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import chess.domain.AbstractTestFixture;

public class DirectionsTest extends AbstractTestFixture {

    @DisplayName("수직이나 수평으로 양방향이면 예외를 던진다")
    @ParameterizedTest(name = "{0}, {1}")
    @CsvSource({"UP,DOWN", "LEFT,RIGHT"})
    void horizontalOrVertical_bidirectional_throws(Direction direction, Direction direction2) {
        assertThatThrownBy(() -> new Directions(List.of(direction, direction2)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("수직이나 수평으로 양방향이면 안됩니다");
    }

    @DisplayName("특정 방향이 몇 개인지 센다")
    @Test
    void countDirection() {
        Directions upAndRight = new Directions(List.of(UP, UP, RIGHT, RIGHT, RIGHT));

        assertThat(upAndRight.count(RIGHT)).isEqualTo(3);
    }

    @DisplayName("특정 축의 방향이 몇 개인지 센다")
    @Test
    void countDirectionsInAxis() {
        Directions upAndRight = new Directions(List.of(UP, UP, RIGHT, RIGHT, RIGHT));

        assertThat(upAndRight.countDirectionsIn(Axis.HORIZON)).isEqualTo(3);
    }

    @DisplayName("목적지를 찾을 수 있다")
    @Test
    void findDestination() {
        var directions = new Directions(List.of(RIGHT, RIGHT, UP, UP, UP));
        var destination = directions.move(createPosition("A,ONE"));

        assertThat(destination).isEqualTo(createPosition("C,FOUR"));
    }

    @DisplayName("방향 컬렉션끼리 합칠 수 있다")
    @Test
    void joinDirections() {
        var directions = new Directions(List.of(RIGHT, RIGHT, UP));
        var other = new Directions(List.of(RIGHT, RIGHT, RIGHT));

        assertThat(directions.join(other)).isEqualTo(new Directions(List.of(RIGHT, RIGHT, RIGHT, RIGHT, RIGHT, UP)));
    }

    static Stream<Arguments> countMinimumUnits() {
        return Stream.of(
                Arguments.of(List.of(RIGHT, RIGHT, UP), 1),
                Arguments.of(List.of(RIGHT, UP), 1),
                Arguments.of(List.of(LEFT, LEFT), 2),
                Arguments.of(List.of(LEFT, LEFT), 2)
        );
    }

    @DisplayName("단위가 몇 개인지 셀 수 있다")
    @ParameterizedTest
    @MethodSource
    void countMinimumUnits(List<Direction> directions, int expectedCount) {
        assertThat(new Directions(directions).countMinimumUnits()).isEqualTo(expectedCount);
    }
}
