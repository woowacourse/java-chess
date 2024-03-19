package chess.domain.direction;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.Position;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RightDirectionTest {

    @Test
    @DisplayName("오른쪽으로 이동한다.")
    void move() {
        Direction direction = new RightDirection();
        Position from = new Position(1, 1);

        assertThat(direction.move(from)).isEqualTo(List.of(new Position(2, 1)));
    }
}
