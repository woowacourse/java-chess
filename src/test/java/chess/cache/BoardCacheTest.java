package chess.cache;

import chess.domain.Position;
import chess.domain.piece.Piece;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class BoardCacheTest {
    @DisplayName("보드의 크기는 8 * 8이다.")
    @Test
    void boardSizeHas64() {
        final Map<Position, Piece> boards = BoardCache.create();

        assertThat(boards).hasSize(64);
    }
}
