package chess.domain.chesspiece;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static chess.domain.game.Team.WHITE;
import static org.assertj.core.api.Assertions.assertThat;

public class QueenTest {
    @Test
    @DisplayName("Queen 생성")
    void create() {
        assertThat(new Queen(WHITE)).isInstanceOf(Queen.class);
    }
}
