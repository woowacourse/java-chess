package chess.domain.piece;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import chess.domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class QueenTest {
    
    @Test
    @DisplayName("퀸 움직일 수 있는 위치 테스트")
    void canMove() {
        Queen queen = Queen.create(Color.WHITE);
        assertDoesNotThrow(() ->
                queen.canMove(Position.from("a1"), Position.from("b2"))
        );
    }
    
    @Test
    @DisplayName("퀸 움직일 수 없는 위치 테스트")
    void canNotMove() {
        Queen queen = Queen.create(Color.WHITE);
        assertThrows(IllegalArgumentException.class, () ->
                queen.canMove(Position.from("a1"), Position.from("b3"))
        );
    }
    
}