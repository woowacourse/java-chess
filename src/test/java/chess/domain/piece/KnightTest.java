package chess.domain.piece;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import chess.domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class KnightTest {
    
    @Test
    @DisplayName("나이트 움직일 수 있는 위치 테스트")
    void canMove() {
        Knight knight = Knight.create(Color.WHITE);
        assertDoesNotThrow(() ->
                knight.canMove(Position.from("a1"), Position.from("b3"))
        );
    }
    
    @Test
    @DisplayName("나이트 움직일 수 없는 위치 테스트")
    void canNotMove() {
        Knight knight = Knight.create(Color.WHITE);
        assertThrows(IllegalArgumentException.class, () ->
                knight.canMove(Position.from("a1"), Position.from("b2"))
        );
    }
    
}