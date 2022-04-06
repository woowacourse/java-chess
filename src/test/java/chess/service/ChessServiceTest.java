package chess.service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ChessServiceTest {

    @Test
    @DisplayName("체스 게임을 만들 수 있다.")
    public void save() {
        final ChessService chessService = new ChessService();

        assertDoesNotThrow(() -> chessService.createRoom());
    }
}
