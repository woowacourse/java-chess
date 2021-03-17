package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.Team;
import org.junit.jupiter.api.Test;

class QueenTest {
    @Test
    void team() {
        Queen queen = new Queen(Team.BLACK);
        assertThatThrownBy(() -> queen.checkTurn(Team.WHITE))
            .isInstanceOf(IllegalArgumentException.class);
    }
}