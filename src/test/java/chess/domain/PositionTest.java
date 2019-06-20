package chess.domain;

import chess.exception.NotFoundPositionException;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PositionTest {

    @Test
    public void create() {
        Position position = Position.of("11");
        assertThat(position).isEqualTo(Position.of("11"));
    }

    @Test
    public void 존재하지않는_좌표를_입력했을_때_예외처리() {
        assertThrows(NotFoundPositionException.class, () -> {
            Position.of("123");
        });
    }
}
