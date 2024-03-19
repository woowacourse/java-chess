package chess.domain.direction;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import chess.domain.Position;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LeftDirectionTest {

    @Test
    @DisplayName("왼쪽으로 이동한다.")
    void move() {
        Direction direction = new LeftDirection();
        Position from = new Position(2, 1);

        assertThat(direction.move(from)).isEqualTo(List.of(new Position(1, 1)));
    }
}
