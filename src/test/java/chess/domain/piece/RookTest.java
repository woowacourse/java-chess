package chess.domain.piece;

import static chess.domain.board.MoveType.MOVE;
import static chess.domain.move.Direction.DOWN;
import static chess.domain.move.Direction.LEFT;
import static chess.domain.move.Direction.RIGHT;
import static chess.domain.move.Direction.UP;
import static chess.domain.piece.Color.WHITE;
import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.move.Move;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class RookTest {

    @DisplayName("가로/세로 여러 칸 움직일 수 있다.")
    @Test
    void canMove_HorizontalVertical_Infinite() {
        Rook rook = new Rook(WHITE);

        assertThat(rook.isValidMove(new Move(LEFT, LEFT, LEFT), MOVE)).isTrue();
        assertThat(rook.isValidMove(new Move(RIGHT, RIGHT), MOVE)).isTrue();
        assertThat(rook.isValidMove(new Move(UP, UP), MOVE)).isTrue();
        assertThat(rook.isValidMove(new Move(DOWN), MOVE)).isTrue();
    }

    @DisplayName("자신의 수가 아닌 움직임을 할 수 없다.")
    @Test
    void canNotMove() {
        Rook rook = new Rook(WHITE);

        assertThat(rook.isValidMove(new Move(LEFT, LEFT, UP), MOVE)).isFalse();
        assertThat(rook.isValidMove(new Move(LEFT, UP), MOVE)).isFalse();
        assertThat(rook.isValidMove(new Move(RIGHT, RIGHT, DOWN, DOWN), MOVE)).isFalse();
    }
}
