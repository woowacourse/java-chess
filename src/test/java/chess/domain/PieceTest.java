package chess.domain;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PieceTest {

    @DisplayName("기물 생성 테스트")
    @Test
    void construct() {
        assertDoesNotThrow(() -> new Pawn(Team.BLACK));
    }
}
