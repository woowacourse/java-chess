package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThatCode;

import chess.domain.player.TeamType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class QueenTest {
    @DisplayName("생성 테스트")
    @Test
    void makeQueen() {
        assertThatCode(() -> new Queen(TeamType.BLACK))
            .doesNotThrowAnyException();
    }
}