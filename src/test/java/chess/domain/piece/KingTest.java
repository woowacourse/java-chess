package chess.domain.piece;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import chess.domain.Color;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class KingTest {

    @DisplayName("King을 생성한다.")
    @Test
    void King을_생성한다() {
        assertDoesNotThrow(() -> new King(Color.BLACK));
    }
}
