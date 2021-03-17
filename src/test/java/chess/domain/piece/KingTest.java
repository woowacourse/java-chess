package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThatCode;

import chess.domain.player.TeamType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class KingTest {
    @DisplayName("생성 테스트")
    @Test
    void makeKing() {
        assertThatCode(() -> new King(TeamType.BLACK))
            .doesNotThrowAnyException();
    }
}