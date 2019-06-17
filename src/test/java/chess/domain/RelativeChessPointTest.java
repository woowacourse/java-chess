package chess.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RelativeChessPointTest {
    @Test
    void of() {
        assertThat(RelativeChessPoint.of(1, 2)).isEqualTo(RelativeChessPoint.of(1, 2));
    }
}