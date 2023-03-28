package chess.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

class DirectionTest {

    @Test
    @DisplayName("source Position과 target Poistion을 입력할 때 이동 방향이 반환된다.")
    void shouldSucceedToFindDirectionFromSourcePositionToTargetPosition() {
        Position sourcePosition1 = Position.findPosition("d3");
        Position targetPosition1 = Position.findPosition("d1");
        Direction expectedDirection1 = Direction.SOUTH;

        Position sourcePosition2 = Position.findPosition("d3");
        Position targetPosition2 = Position.findPosition("c3");
        Direction expectedDirection2 = Direction.WEST;

        Position sourcePosition3 = Position.findPosition("b1");
        Position targetPosition3 = Position.findPosition("b3");
        Direction expectedDirection3 = Direction.NORTH;

        Position targetPosition4 = Position.findPosition("e3");
        Position sourcePosition4 = Position.findPosition("d3");
        Direction expectedDirection4 = Direction.EAST;

        Position sourcePosition5 = Position.findPosition("d3");
        Position targetPosition5 = Position.findPosition("b1");
        Direction expectedDirection5 = Direction.SOUTH_WEST;

        Position sourcePosition6 = Position.findPosition("d3");
        Position targetPosition6 = Position.findPosition("e4");
        Direction expectedDirection6 = Direction.NORTH_EAST;

        Position sourcePosition7 = Position.findPosition("d3");
        Position targetPosition7 = Position.findPosition("e2");
        Direction expectedDirection7 = Direction.SOUTH_EAST;

        Position sourcePosition8 = Position.findPosition("d3");
        Position targetPosition8 = Position.findPosition("c4");
        Direction expectedDirection8 = Direction.NORTH_WEST;

        Position sourcePosition9 = Position.findPosition("d3");
        Position targetPosition9 = Position.findPosition("e5");
        Direction expectedDirection9 = Direction.NORTH_NORTH_EAST;

        Position sourcePosition10 = Position.findPosition("d3");
        Position targetPosition10 = Position.findPosition("f2");
        Direction expectedDirection10 = Direction.SOUTH_EAST_EAST;

        assertAll(
                () -> assertThat(Direction.findDirectionFromSourceToTarget(sourcePosition1, targetPosition1)).isEqualTo(expectedDirection1),
                () -> assertThat(Direction.findDirectionFromSourceToTarget(sourcePosition2, targetPosition2)).isEqualTo(expectedDirection2),
                () -> assertThat(Direction.findDirectionFromSourceToTarget(sourcePosition3, targetPosition3)).isEqualTo(expectedDirection3),
                () -> assertThat(Direction.findDirectionFromSourceToTarget(sourcePosition4, targetPosition4)).isEqualTo(expectedDirection4),
                () -> assertThat(Direction.findDirectionFromSourceToTarget(sourcePosition5, targetPosition5)).isEqualTo(expectedDirection5),
                () -> assertThat(Direction.findDirectionFromSourceToTarget(sourcePosition6, targetPosition6)).isEqualTo(expectedDirection6),
                () -> assertThat(Direction.findDirectionFromSourceToTarget(sourcePosition7, targetPosition7)).isEqualTo(expectedDirection7),
                () -> assertThat(Direction.findDirectionFromSourceToTarget(sourcePosition8, targetPosition8)).isEqualTo(expectedDirection8),
                () -> assertThat(Direction.findDirectionFromSourceToTarget(sourcePosition9, targetPosition9)).isEqualTo(expectedDirection9),
                () -> assertThat(Direction.findDirectionFromSourceToTarget(sourcePosition10, targetPosition10)).isEqualTo(expectedDirection10)
        );
    }

    @Test
    @DisplayName("이동 방향이 올바르지 않을 때 예외가 발생한다.")
    void shouldFailToFindDirectionFromSourcePositionToTargetPosition() {
        Position sourcePosition1 = Position.findPosition("e6");
        Position targetPosition1 = Position.findPosition("d1");

        Position sourcePosition2 = Position.findPosition("d3");
        Position targetPosition2 = Position.findPosition("a8");

        assertAll(
                () -> assertThatThrownBy(() -> Direction.findDirectionFromSourceToTarget(sourcePosition1, targetPosition1))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessage("[ERROR] 이동 가능한 방향이 존재하지 않습니다."),
                () -> assertThatThrownBy(() -> Direction.findDirectionFromSourceToTarget(sourcePosition2, targetPosition2))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessage("[ERROR] 이동 가능한 방향이 존재하지 않습니다.")
        );
    }
}