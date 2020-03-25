package chess.board.piece;

import chess.board.Vector;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BishopTest {

    @DisplayName("비숍 움직일 수 있는지 확인")
    @Test
    void canMove() {
        Bishop bishop = new Bishop(Team.BLACK);
        assertThat(bishop.canMove(new Vector(3, -3))).isTrue();
    }

    @Test
    void findPath() {
        Bishop bishop = new Bishop(Team.BLACK);
        assertThat(bishop.findPath(new Vector(3, -3)))
                .containsExactly(Direction.RIGHT_DOWN, Direction.RIGHT_DOWN, Direction.RIGHT_DOWN);
    }
}