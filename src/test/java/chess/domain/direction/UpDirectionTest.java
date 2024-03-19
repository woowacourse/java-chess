package chess.domain.direction;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.Position;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class UpDirectionTest {

    @Test
    @DisplayName("한칸 위로 이동한다.")
    void move() {
        Direction direction = new UpDirection();
        Position from = new Position(1, 1);

        assertThat(direction.move(from)).isEqualTo(List.of(new Position(1, 2)));
    }
}
