package chess.domain;

import chess.domain.exception.InvalidIndexException;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;


class SpotTest {
    @Test
    void 캐싱_확인() {
        Spot spotA = Spot.valueOf(7, 1);
        assertThat(spotA == Spot.valueOf(7, 1)).isTrue();
    }

    @Test
    void 최저값_예외_검사() {
        assertThrows(InvalidIndexException.class, () -> {
            Spot.valueOf(-1, -1);
        });
    }

    @Test
    void 최대값_예외_검사() {
        assertThrows(InvalidIndexException.class, () -> {
            Spot.valueOf(8, 8);
        });
    }
}