package chess.domain.piece;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GamePiecesTest {

    @Test
    @DisplayName("플레이어별 game piece 생성")
    void getGamePieces() {
        assertThat(GamePieces.createGamePieces()).hasSize(12);
    }
}