package chess.domain.chesspiece;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static chess.domain.game.Team.WHITE;
import static org.assertj.core.api.Assertions.assertThat;

public class PawnTest {
    @Test
    @DisplayName("Pawn 생성")
    void create() {
        assertThat(new Pawn(WHITE)).isInstanceOf(Pawn.class);
    }
}
