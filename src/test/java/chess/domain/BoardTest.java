package chess.domain;

import static org.junit.jupiter.api.Assertions.*;

import chess.domain.piece.Piece;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BoardTest {
    @Test
    @DisplayName("체스판을 생성한다.")
    void createBoard() {
        Map<Square, Piece> pieces = new HashMap<>();
        assertDoesNotThrow(() -> new Board(pieces));
    }
}
