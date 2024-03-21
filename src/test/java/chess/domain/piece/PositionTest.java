package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import chess.domain.piece.Direction;
import chess.domain.piece.Position;
import java.util.Set;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class PositionTest {

    @Test
    @DisplayName("(1,1) 에서 (8,8)의 좌표를 생성할 수 있다.")
    void createPosition() {
        Assertions.assertThatCode(() -> new Position(1, 1))
                .doesNotThrowAnyException();
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 9})
    @DisplayName("(1, 1) 에서 (8, 8)의 범위를 벗어난 열의 좌표를 생성할 수 있다.")
    void createPositionByInvalidateColumnRange(int x) {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new Position(x, 1))
                .withMessage("올바르지 않은 열입니다.");
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 9})
    @DisplayName("(1, 1) 에서 (8, 8)의 범위를 벗어난 행의 좌표를 생성할 수 있다.")
    void createPositionByInvalidateRowRange(int y) {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new Position(1, y))
                .withMessage("올바르지 않은 행입니다.");
    }

    @Test
    @DisplayName("현재 위치가 (1, 1)이고, Direction이 DOWN이면 움직일 수 있는 좌표가 없다.")
    void findMovablePositions() {
        Position position = new Position(1, 1);

        assertThat(position.findMovablePositions(Set.of(Direction.DOWN))).hasSize(0);
    }

    @Test
    @DisplayName("현재 위치가 (1, 1)이고, Direction이 UP, RIGHT이면 (2, 1), (1, 2)로 이동할 수 있다.")
    void findPathToMovableDirections() {
        Position position = new Position(1, 1);

        assertThat(position.findMovablePositions(Set.of(Direction.UP, Direction.RIGHT)))
                .containsExactly(new Position(2, 1), new Position(1, 2));
    }

    @Test
    @DisplayName("현재 위치가 (1, 1)이고, Destination이 (1, 3), 방향이 UP이다.")
    void findDirectionToUp() {
        Position position = new Position(1, 1);

        assertThat(position.findDirectionTo(new Position(1, 3))).isEqualTo(Direction.UP);
    }

    @Test
    @DisplayName("현재 위치가 (1, 1)이고, Destination이 (3, 1), 방향이 RIGHT이다.")
    void findDirectionToRight() {
        Position position = new Position(1, 1);

        assertThat(position.findDirectionTo(new Position(3, 1))).isEqualTo(Direction.RIGHT);
    }

    @Test
    @DisplayName("현재 위치가 (1, 1)이고, Destination이 (2, 4), 여덟 방향에 속하지 않으므로 예외를 던진다.")
    void findDirectionByNotInEightDirection() {
        Position position = new Position(1, 1);

        assertThatIllegalArgumentException()
                .isThrownBy(() -> position.findDirectionTo(new Position(2, 4)))
                .withMessage("이동할 수 없는 방향입니다.");
    }

    @Test
    @DisplayName("현재 위치가 (1, 1)이고, Direction이 UP, Destination이 (1, 3), 이동할 수 있는 좌표 값들 : (1, 2)를 반환한다.")
    void forwardToDirection() {
        Position position = new Position(1, 1);

        assertThat(position.findCourses(Direction.UP, new Position(1, 3))).containsExactlyInAnyOrder(
                new Position(1, 2));
    }

    @Test
    @DisplayName("현재 위치가 (1, 1)이고, Direction이 RIGHT_UP, Destination이 (4, 4), 이동할 수 있는 좌표 값들 : (2, 2), (3, 3)을 반환한다.")
    void forwardToDirectionRightUp() {
        Position position = new Position(1, 1);

        assertThat(position.findCourses(Direction.RIGHT_UP, new Position(4, 4))).containsExactlyInAnyOrder(
                new Position(2, 2), new Position(3, 3));
    }
}