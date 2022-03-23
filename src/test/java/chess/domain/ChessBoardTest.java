package chess.domain;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ChessBoardTest {

    @DisplayName("체스 보드를 생성한다.")
    @Test
    void 체스보드를_생성한다() {
        assertDoesNotThrow(() -> new ChessBoard());
    }
}
