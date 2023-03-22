package chess.domain.move;

import static chess.domain.move.Direction.RIGHT;
import static chess.domain.move.Direction.UP;
import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import chess.domain.AbstractTestFixture;
import chess.domain.position.Position;

public class UnitMoveTest extends AbstractTestFixture {

    @DisplayName("목적지를 찾을 수 있다")
    @Test
    void findDestination() {
        UnitMove unitMove = UnitMove.of(Directions.of(RIGHT, RIGHT, UP, UP, UP));
        Position destination = unitMove.move(createPosition("A,ONE"));

        assertThat(destination).isEqualTo(createPosition("C,FOUR"));
    }

    static Stream<Arguments> not_unit_move_throws() {
        return Stream.of(
                Arguments.of(List.of(UP, UP)),
                Arguments.of(List.of(RIGHT, RIGHT)),
                Arguments.of(List.of(UP, UP, RIGHT, RIGHT))
        );
    }

    @DisplayName("단위 수가 아니면 예외를 던진다")
    @ParameterizedTest
    @MethodSource
    void not_unit_move_throws(List<Direction> directions) {
        assertThatThrownBy(() -> new UnitMove(new Directions(directions)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("한 단위가 아닙니다");
    }

    static Stream<Arguments> unit_move_does_not_throw() {
        return Stream.of(
                Arguments.of(List.of(UP, RIGHT)),
                Arguments.of(List.of(UP, UP, UP, RIGHT)),
                Arguments.of(List.of(UP, UP, UP, UP, UP, UP, UP, RIGHT, RIGHT, RIGHT))
        );
    }

    @DisplayName("단위 수는 정상 생성된다")
    @ParameterizedTest
    @MethodSource
    void unit_move_does_not_throw(List<Direction> directions) {
        assertThatNoException().isThrownBy(() -> UnitMove.of(new Directions(directions)));
    }

    @DisplayName("반복할 수 있다.")
    @Test
    void repeat() {
        var unitMove = UnitMove.of(new Directions(List.of(RIGHT, RIGHT, UP)));

        assertThat(unitMove.repeat(2)).containsExactly(unitMove, unitMove);
    }

    @DisplayName("방향을 가져야 한다")
    @Test
    void noDirection_throws() {
        assertThatThrownBy(() -> UnitMove.of(new Directions(List.of())))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("방향이 존재해야합니다");
    }

    @DisplayName("방향들의 단위 수를 만들 수 있다")
    @Test
    void createUnitMoveFromDirections() {
        var directions = new Directions(List.of(RIGHT, RIGHT, UP, RIGHT, RIGHT, UP));
        var unitDirections = new Directions(List.of(RIGHT, RIGHT, UP));

        assertThat(UnitMove.of(directions)).isEqualTo(UnitMove.of(unitDirections));
    }
}