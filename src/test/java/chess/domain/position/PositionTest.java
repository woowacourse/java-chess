package chess.domain.position;

import chess.domain.Direction;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class PositionTest {
    @Test
    void 체스판_밖으로_움직일_경우() {
        Position position = Position.of("e8");

        assertThatThrownBy(() -> {
            position.moveTo(Direction.NORTH);

        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("범위를 초과하였습니다.");

    }
}
