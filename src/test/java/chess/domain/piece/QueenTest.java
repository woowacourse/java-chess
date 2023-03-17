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

public class QueenTest {

    @DisplayName("가로/세로/대각선 여러 칸 움직일 수 있다.")
    @Test
    void canMove_HorizontalVerticalDiagonal_Infinite() {
        Queen queen = new Queen(WHITE);

        assertThat(queen.isValidMove(new Move(LEFT, LEFT, LEFT), MOVE)).isTrue();
        assertThat(queen.isValidMove(new Move(RIGHT, RIGHT), MOVE)).isTrue();
        assertThat(queen.isValidMove(new Move(UP, UP), MOVE)).isTrue();
        assertThat(queen.isValidMove(new Move(DOWN), MOVE)).isTrue();
        assertThat(queen.isValidMove(new Move(UP, RIGHT, UP, RIGHT, UP, RIGHT), MOVE)).isTrue();
        assertThat(queen.isValidMove(new Move(UP, LEFT), MOVE)).isTrue();
        assertThat(queen.isValidMove(new Move(DOWN, RIGHT), MOVE)).isTrue();
        assertThat(queen.isValidMove(new Move(DOWN, LEFT), MOVE)).isTrue();
    }

    @DisplayName("자신의 수가 아닌 움직임을 할 수 없다.")
    @Test
    void canNotMove() {
        Queen queen = new Queen(WHITE);

        assertThat(queen.isValidMove(new Move(LEFT, LEFT, UP), MOVE)).isFalse();
    }
}
