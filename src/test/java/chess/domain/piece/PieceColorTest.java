package chess.domain.piece;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PieceColorTest {

    @DisplayName("색깔 이름 getter 테스트")
    @Test
    void getName_ColorName() {
        assertEquals(PieceColor.BLACK.getFlag(), "B");
        assertEquals(PieceColor.WHITE.getFlag(), "W");
    }
}