package chess.domain.piece;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import chess.domain.Color;
import chess.domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class KingTest {
    
    @Test
    @DisplayName("킹 움직일 수 있는 위치 테스트")
    void canMove() {
        King king = King.create(Color.WHITE);
        assertDoesNotThrow(() ->
                king.canMove(Position.from("a1"), Position.from("b2"))
        );
    }
    
    @Test
    @DisplayName("킹 움직일 수 없는 위치 테스트")
    void canNotMove() {
        King king = King.create(Color.WHITE);
        assertThrows(IllegalArgumentException.class, () ->
                king.canMove(Position.from("a1"), Position.from("b3"))
        );
    }
    
}
