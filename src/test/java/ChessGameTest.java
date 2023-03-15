import chess.ChessBoard;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ChessGameTest {

    @Test
    @DisplayName("체스 게임을 올바르게 생성한다.")
    void shouldSuccessGenerateChessGame() {
        assertDoesNotThrow(() -> new ChessGame(ChessBoard.generateChessBoard()));
    }
}
