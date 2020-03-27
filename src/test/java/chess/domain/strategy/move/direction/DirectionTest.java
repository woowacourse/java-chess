package chess.domain.strategy.move.direction;

import chess.domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class DirectionTest {
    @DisplayName("위 방향 이동")
    @Test
    void checkUpStrategy() {
        Position source = Position.of("a1");
        Position target = Position.of("a8");
        assertThat(Direction.findDirection(source, target)).isEqualTo(Direction.UP);
    }

    @DisplayName("아래 방향 이동")
    @Test
    void checkDownStrategy() {
        Position source = Position.of("a8");
        Position target = Position.of("a1");
        assertThat(Direction.findDirection(source, target)).isEqualTo(Direction.DOWN);
    }

    @DisplayName("왼쪽 방향 이동")
    @Test
    void checkLeftStrategy() {
        Position source = Position.of("e5");
        Position target = Position.of("b5");
        assertThat(Direction.findDirection(source, target)).isEqualTo(Direction.LEFT);
    }

    @DisplayName("오른쪽 방향 이동")
    @Test
    void checkRightStrategy() {
        Position source = Position.of("b5");
        Position target = Position.of("e5");
        assertThat(Direction.findDirection(source, target)).isEqualTo(Direction.RIGHT);
    }

    @DisplayName("왼쪽 위 방향 이동")
    @Test
    void checkLeftUpStrategy() {
        Position source = Position.of("e1");
        Position target = Position.of("c3");
        assertThat(Direction.findDirection(source, target)).isEqualTo(Direction.LEFT_UP);
    }

    @DisplayName("왼쪽 아래 방향 이동")
    @Test
    void checkLeftDownStrategy() {
        Position source = Position.of("h8");
        Position target = Position.of("a1");
        assertThat(Direction.findDirection(source, target)).isEqualTo(Direction.LEFT_DOWN);
    }

    @DisplayName("오른쪽 위 방향 이동")
    @Test
    void checkRightUpStrategy() {
        Position source = Position.of("a1");
        Position target = Position.of("h8");
        assertThat(Direction.findDirection(source, target)).isEqualTo(Direction.RIGHT_UP);
    }

    @DisplayName("오른쪽 아래 방향 이동")
    @Test
    void checkRightDownStrategy() {
        Position source = Position.of("a8");
        Position target = Position.of("e1");
        assertThat(Direction.findDirection(source, target)).isEqualTo(Direction.RIGHT_DOWN);
    }
}