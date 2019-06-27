package chess.domain.chesspoint;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ChessRowTest {
    @Test
    void of_캐시여부() {
        assertThat(ChessRow.of(1) == ChessRow.of(1)).isTrue();
    }
}