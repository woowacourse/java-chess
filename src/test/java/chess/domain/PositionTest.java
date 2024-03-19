package chess.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

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
    @DisplayName("(1,1) 에서 (8,8)의 범위를 벗어난 열의 좌표를 생성할 수 있다.")
    void createPositionByInvalidateColumnRange(int x) {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new Position(x, 1))
                .withMessage("올바르지 않은 열입니다.");
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 9})
    @DisplayName("(1,1) 에서 (8,8)의 범위를 벗어난 행의 좌표를 생성할 수 있다.")
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
    void findMovablePositionsByMovableDirections() {
        Position position = new Position(1, 1);

        assertThat(position.findMovablePositions(Set.of(Direction.UP, Direction.RIGHT)))
                .containsExactly(new Position(2, 1), new Position(1, 2));
    }
}