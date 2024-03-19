package chess.domain.direction;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.Position;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LeftDirectionTest {

    @Test
    @DisplayName("좌 방향은 column을 1 감소한다.")
    void move() {
        Direction direction = new LeftDirection();
        Position from = new Position(3, 8);

        assertThat(direction.move(from)).isEqualTo(List.of(new Position(3, 7)));
    }

    @Test
    @DisplayName("column이 최소인 경우 이동할 수 없다.")
    void canNotMove() {
        Direction direction = new LeftDirection();
        Position from = new Position(3, 1);

        assertThat(direction.move(from)).isEmpty();
    }
}
