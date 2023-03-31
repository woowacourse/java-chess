package chess.domain.piece;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import chess.domain.Color;
import chess.domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RookTest {
    
    @Test
    @DisplayName("룩 움직일 수 있는 위치 테스트")
    void canMove() {
        Rook rook = Rook.create(Color.WHITE);
        assertDoesNotThrow(() ->
                rook.canMove(Position.from("a1"), Position.from("a5"))
        );
    }
    
    @Test
    @DisplayName("룩 움직일 수 없는 위치 테스트")
    void canNotMove() {
        Rook rook = Rook.create(Color.WHITE);
        assertThrows(IllegalArgumentException.class, () ->
                rook.canMove(Position.from("a1"), Position.from("b2"))
        );
    }
    
}
