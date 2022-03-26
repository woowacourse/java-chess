package chess.domain.position;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static chess.domain.position.Direction.*;
import static org.assertj.core.api.Assertions.assertThat;

class PositionTest {

    @Test
    @DisplayName("열과 행을 갖는 Position을 생성")
    void position_createWithColumnAndRow() {
        Position position = Position.of("a5");
        assertThat(position).isInstanceOf(Position.class);
    }

    @Test
    @DisplayName("출발지와 목적지의 거리가 1인경우 반환된 경로는 비어있다")
    void calculate_ifDistanceIsOne_pathIsEmpty() {
        Position source = Position.of("a5");
        Position target = Position.of("a6");
        assertThat(source.calculatePath(target, VERTICAL)).isEmpty();
    }

    @Test
    @DisplayName("출발지와 목적지의 수직 거리가 1을 초과하면 경로를 반환한다")
    void calculate_verticalPathPositions() {
        Position source = Position.of("a5");
        Position target = Position.of("a8");
        assertThat(source.calculatePath(target, VERTICAL))
                .containsExactly(Position.of("a6"), Position.of("a7"));
    }

    @Test
    @DisplayName("출발지와 목적지의 수평 거리가 1을 초과하면 경로를 반환한다")
    void calculate_horizontalPathPositions() {
        Position source = Position.of("e5");
        Position target = Position.of("b5");
        assertThat(source.calculatePath(target, HORIZONTAL))
                .containsExactly(Position.of("d5"), Position.of("c5"));
    }

    @Test
    @DisplayName("출발지와 목적지의 대각선 거리가 1을 초과하면 경로를 반환한다")
    void calculate_diagonalPathPositions() {
        Position source = Position.of("e5");
        Position target = Position.of("b2");
        assertThat(source.calculatePath(target, DIAGONAL))
                .containsExactly(Position.of("d4"), Position.of("c3"));
    }
}
