package chess;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class ChessPieceTest {

    @Test
    @DisplayName("Shape와 Side를 입력하면 ChessPiece가 정상적으로 생성된다.")
    void name() {
        for (Shape shape : Shape.values()) {
            for (Side side : Side.values()) {
                assertDoesNotThrow(() -> new ChessPiece(shape, side));
            }
        }
    }
}
