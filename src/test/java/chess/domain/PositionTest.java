package chess.domain;

import chess.domain.exceptions.CoordinateRangeException;
import chess.domain.utils.InputParser;
import org.junit.jupiter.api.Test;

import static chess.domain.utils.InputParser.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PositionTest {
    @Test
    void 벗어나는_위치_예외처리() {
        assertThrows(CoordinateRangeException.class, () -> position("p3"));
    }

    @Test
    void 같은_위치_테스트() {
        Position position = position("a1");
        assertThat(position).isEqualTo(position("a1"));
    }
}
