package chess.domain.direction;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import chess.domain.Position;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DownDirectionTest {

    @Test
    @DisplayName("하 방향은 열로 1 감소한다.")
    void move() {
        Position from = new Position(1, 8);
        Direction direction = new DownDirection();

        assertThat(direction.move(from)).isEqualTo(List.of(new Position(1, 7)));
    }
}
