package chess.domain.direction;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.Position;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class UpDirectionTest {

    @Test
    @DisplayName("상 방향은 row 1 증가한다.")
    void move() {
        Direction direction = new UpDirection();
        Position from = new Position(1, 3);

        assertThat(direction.move(from)).isEqualTo(List.of(new Position(2, 3)));
    }

    @Test
    @DisplayName("row가 최대인 경우 이동할 수 없다.")
    void canNotMove() {
        Direction direction = new UpDirection();
        Position from = new Position(8, 3);

        assertThat(direction.move(from)).isEmpty();
    }
}
