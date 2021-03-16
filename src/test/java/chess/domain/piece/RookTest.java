package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.junit.jupiter.api.Assertions.*;

import chess.domain.player.PlayerType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RookTest {
    @DisplayName("생성 테스트")
    @Test
    void makeRook() {
        assertThatCode(() -> new Rook(PlayerType.BLACK))
            .doesNotThrowAnyException();
    }
}