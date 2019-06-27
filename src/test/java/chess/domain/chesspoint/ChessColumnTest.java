package chess.domain.chesspoint;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ChessColumnTest {
    @Test
    void of_캐시여부() {
        assertThat(ChessColumn.of(1) == ChessColumn.of(1)).isTrue();
    }
}