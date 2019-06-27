package chess.domain.board;

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

    @Test
    public void 계산을_올바르게_수행하는지_테스트() {
        Coordinate coordinate = Coordinate.of(3);
        assertThat(coordinate.calculate(3)).isEqualTo(6);
    }
}
