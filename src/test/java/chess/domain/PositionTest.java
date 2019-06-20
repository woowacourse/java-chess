package chess.domain;

import chess.domain.exceptions.CoordinateRangeException;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PositionTest {
    @Test
    void 벗어나는_위치_예외처리() {
        assertThrows(CoordinateRangeException.class, () -> {
           new Position(new Coordinate('g'), new Coordinate(9));
        });
    }

    @Test
    void 같은_위치_테스트() {
        Position position = new Position(new Coordinate('a'), new Coordinate(1));
        assertThat(position).isEqualTo(new Position(new Coordinate('a'), new Coordinate(1)));
    }
}
