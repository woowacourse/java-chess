package chess.domain.move;

import static chess.domain.move.Direction.RIGHT;
import static chess.domain.move.Direction.UP;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

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

    @DisplayName("최소단위로 분할할 수 있다")
    @Test
    void split() {
        Directions directions = new Directions(List.of(RIGHT, RIGHT, UP, RIGHT, RIGHT, UP));
        Directions expected = new Directions(List.of(RIGHT, RIGHT, UP));

        assertThat(directions.splitIntoMinimumUnit()).isEqualTo(expected);
    }
}
