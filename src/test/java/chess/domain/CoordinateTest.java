package chess.domain;

import chess.exception.CoordinateOutOfBoundsException;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CoordinateTest {
    @Test
    public void create() {
        Coordinate coordinate = Coordinate.of(1);
        assertThat(coordinate == Coordinate.of(1)).isTrue();
    }

    @Test
    public void 범위보다_작은_값을_호출하는_경우_예외처리() {
        assertThrows(CoordinateOutOfBoundsException.class, () -> Coordinate.of(-1));
    }

    @Test
    public void 범위보다_큰_값을_호출하는_경우_예외처리() {
        assertThrows(CoordinateOutOfBoundsException.class, () -> Coordinate.of(8));
    }
}
