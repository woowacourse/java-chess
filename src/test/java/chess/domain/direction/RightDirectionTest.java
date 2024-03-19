package chess.domain.direction;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.Position;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RightDirectionTest {

    @Test
    @DisplayName("우 방향은 column을 1 증가한다.")
    void move() {
        Direction direction = new RightDirection();
        Position from = new Position(3, 1);

        assertThat(direction.move(from)).isEqualTo(List.of(new Position(3, 2)));
    }

    @Test
    @DisplayName("column이 최대인 경우 이동할 수 없다.")
    void canNotMove() {
        Direction direction = new RightDirection();
        Position from = new Position(3, 8);

        assertThat(direction.move(from)).isEmpty();
    }
}
