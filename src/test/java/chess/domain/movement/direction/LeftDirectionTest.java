package chess.domain.movement.direction;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.position.Position;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LeftDirectionTest {

    @Test
    @DisplayName("도착 위치로 이동이 가능할 경우 참을 반환한다.")
    void canReach() {
        LeftDirection direction = new LeftDirection(8);
        Position source = Position.of(8, 1);
        Position target = Position.of(6, 1);
        assertThat(direction.canReach(source, target, List.of())).isTrue();
    }

    @Test
    @DisplayName("현재 위치에서 더이상 이동이 불가능한 경우 거짓을 반환한다.")
    void canNotReachWhenNowIsBoundary() {
        LeftDirection direction = new LeftDirection(8);
        Position source = Position.of(1, 1);
        Position target = Position.of(1, 7);
        assertThat(direction.canReach(source, target, List.of())).isFalse();
    }

    @Test
    @DisplayName("도착위치 중간에 장애물이 있을 경우 거짓을 반환한다.")
    void canNotReachWhenObstacleExist() {
        LeftDirection direction = new LeftDirection(8);
        Position source = Position.of(8, 1);
        Position target = Position.of(6, 1);
        assertThat(direction.canReach(source, target, List.of(Position.of(7, 1)))).isFalse();
    }

    @Test
    @DisplayName("이동할 수 있는 방향의 개수를 모두 소진함에도 불구하고 도달하지 못할 경우 거짓을 반환한다.")
    void canNotReachWhenOverMoveCount() {
        LeftDirection direction = new LeftDirection(1);
        Position source = Position.of(8, 1);
        Position target = Position.of(6, 1);
        assertThat(direction.canReach(source, target, List.of())).isFalse();
    }
}
