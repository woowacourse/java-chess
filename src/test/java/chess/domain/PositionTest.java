package chess.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PositionTest {
    @Test
    public void Position_잘_생성하는지_확인한다() {
        Position position = new Position(8, 8);
        assertThat(position).isExactlyInstanceOf(Position.class);
    }

    @Test
    public void 체스판을_벗어난_값이_입력됐을때_예외를_잘_던지는지_확인한다() {
        assertThatThrownBy(() -> {
            new Position(0, 0);
        }).isInstanceOf(IllegalArgumentException.class);
    }
}