package chess.domain.direction;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.Position;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DownDirectionTest {

    @Test
    @DisplayName("하 방향은 row를 1 감소한다.")
    void move() {
        Position from = new Position(8, 3);
        Direction direction = new DownDirection();

        assertThat(direction.move(from)).isEqualTo(List.of(new Position(7, 3)));
    }

    @Test
    @DisplayName("위치의 row 값이 최소인 경우 이동할 수 없다.")
    void canNotMove() {
        Position from = new Position(1, 3);
        Direction direction = new DownDirection();

        assertThat(direction.move(from)).isEmpty();
    }
}
