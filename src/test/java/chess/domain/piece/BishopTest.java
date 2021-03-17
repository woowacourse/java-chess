package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThatCode;

import chess.domain.player.TeamType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BishopTest {

    @DisplayName("생성 테스트")
    @Test
    void makeBishop() {
        assertThatCode(() -> new Bishop(TeamType.BLACK))
            .doesNotThrowAnyException();
    }
}