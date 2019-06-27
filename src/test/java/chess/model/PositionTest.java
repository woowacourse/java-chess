package chess.model;

import chess.model.exception.IllegalPositionException;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PositionTest {
    @Test
    void X_위치_1_미만_테스트() {
        assertThrows(IllegalPositionException.class, () -> Position.valueOf(0));
    }

    @Test
    void X_위치_8_초과_테스트() {
        assertThrows(IllegalPositionException.class, () -> Position.valueOf(9));
    }

    @Test
    void X_equals_테스트() {
        assertThat(Position.valueOf(1)).isEqualTo(Position.valueOf(1));
    }

    @Test
    void source_target의_x값_차의_절대값_비교() {
        Position source = Position.valueOf(3);
        Position target = Position.valueOf(6);

        int actual = source.calculateDiff(target);
        assertThat(actual).isEqualTo(3);
    }
}
