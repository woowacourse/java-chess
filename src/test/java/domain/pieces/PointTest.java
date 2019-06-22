package domain.pieces;

import chess.domain.PointFactory;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class PointTest {

    @Test
    void 올바른_생성() {
        assertDoesNotThrow(() -> PointFactory.of("b1"));
    }

    @Test
    void 숫자2개로_올바른_생성() {
        assertThat(PointFactory.of(1, 1)).isEqualTo(PointFactory.of("a1"));
    }
}