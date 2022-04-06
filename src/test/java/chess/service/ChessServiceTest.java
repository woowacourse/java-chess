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

    @Test
    @DisplayName("진행 중인 체스 게임을 불러올 수 있다.")
    public void load() {
        final ChessService chessService = new ChessService();

        assertDoesNotThrow(() -> chessService.load(1L));
    }

}
