package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import chess.domain.Color;
import chess.domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BishopTest {
    
    @Test
    @DisplayName("비숍 움직일 수 있는 위치 테스트")
    void canMove() {
        Bishop bishop = Bishop.create(Color.WHITE);
        assertDoesNotThrow(() ->
                bishop.canMove(Position.from("a1"), Position.from("b2"))
        );
    }
    
    @Test
    @DisplayName("비숍 움직일 수 없는 위치 테스트")
    void canNotMove() {
        Bishop bishop = Bishop.create(Color.WHITE);
        assertThatThrownBy(() ->
                bishop.canMove(Position.from("a1"), Position.from("b3"))
        ).isInstanceOf(IllegalArgumentException.class);
    }
}
