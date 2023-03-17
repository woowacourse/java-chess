package chess.domain.piece;


import static chess.domain.move.Direction.DOWN;
import static chess.domain.move.Direction.LEFT;
import static chess.domain.move.Direction.RIGHT;
import static chess.domain.move.Direction.UP;
import static chess.domain.piece.Color.WHITE;
import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.move.Move;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BishopTest {

    @DisplayName("대각선 여러 칸 움직일 수 있다.")
    @Test
    void canMove_Diagonal_Infinite() {
        Bishop bishop = new Bishop(WHITE);

        assertThat(bishop.hasMove(new Move(UP, RIGHT, UP, RIGHT, UP, RIGHT))).isTrue();
        assertThat(bishop.hasMove(new Move(UP, LEFT))).isTrue();
        assertThat(bishop.hasMove(new Move(DOWN, RIGHT))).isTrue();
        assertThat(bishop.hasMove(new Move(DOWN, LEFT))).isTrue();
    }

    @DisplayName("자신의 수가 아닌 움직임을 할 수 없다.")
    @Test
    void canNotMove() {
        Bishop bishop = new Bishop(WHITE);

        assertThat(bishop.hasMove(new Move(LEFT, LEFT, LEFT))).isFalse();
        assertThat(bishop.hasMove(new Move(RIGHT, RIGHT))).isFalse();
        assertThat(bishop.hasMove(new Move(UP, UP))).isFalse();
        assertThat(bishop.hasMove(new Move(DOWN))).isFalse();
        assertThat(bishop.hasMove(new Move(LEFT, LEFT, UP))).isFalse();
    }
}
