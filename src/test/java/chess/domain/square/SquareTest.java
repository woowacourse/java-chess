package chess.domain.square;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class SquareTest {

    @DisplayName("Square를 생성한다.")
    @Test
    void createSquare() {
        assertDoesNotThrow(() -> Square.of(File.A, Rank.ONE));
    }
}
