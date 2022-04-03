package chess.domain.position;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class PositionTest {

    @Test
    @DisplayName("열과 행을 갖는 Position을 생성")
    void position_createWithColumnAndRow() {
        final var position = Position.of("a5");
        assertThat(position).isInstanceOf(Position.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {"aa", "12", "a9", "z1", "a12"})
    @DisplayName("올바르지 않은 열 또는 행 입력 시 예외 발생")
    void position_wrongColumnOrRow_throwException(String source) {
        assertThatThrownBy(() -> Position.of(source))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("출발지와 목적지의 거리가 1인경우 반환된 경로는 비어있다")
    void calculate_ifDistanceIsOne_pathIsEmpty() {
        final var source = Position.of("a5");
        final var target = Position.of("a6");
        assertThat(source.positionsInPathToTarget(target, Direction.VERTICAL)).isEmpty();
    }

    @Test
    @DisplayName("출발지와 목적지의 수직 거리가 1을 초과하면 경로를 반환한다")
    void calculate_verticalPathPositions() {
        final var source = Position.of("a5");
        final var target = Position.of("a8");
        assertThat(source.positionsInPathToTarget(target, Direction.VERTICAL))
                .containsExactly(Position.of("a6"), Position.of("a7"));
    }

    @Test
    @DisplayName("출발지와 목적지의 수평 거리가 1을 초과하면 경로를 반환한다")
    void calculate_horizontalPathPositions() {
        final var source = Position.of("e5");
        final var target = Position.of("b5");
        assertThat(source.positionsInPathToTarget(target, Direction.HORIZONTAL))
                .containsExactly(Position.of("d5"), Position.of("c5"));
    }

    @Test
    @DisplayName("출발지와 목적지의 대각선 거리가 1을 초과하면 경로를 반환한다")
    void calculate_diagonalPathPositions() {
        final var source = Position.of("e5");
        final var target = Position.of("b2");
        assertThat(source.positionsInPathToTarget(target, Direction.DIAGONAL))
                .containsExactly(Position.of("d4"), Position.of("c3"));
    }
}
