package chess.domain.piece;

import static chess.domain.board.MoveType.MOVE;
import static chess.domain.piece.Color.WHITE;
import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.position.Move;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class QueenTest {

    @DisplayName("가로/세로/대각선 여러 칸 움직일 수 있다.")
    @Test
    void canMove_HorizontalVerticalDiagonal_Infinite() {
        Queen queen = new Queen(WHITE);

        assertThat(queen.isValidMove(new Move(3, 0), MOVE)).isTrue();
        assertThat(queen.isValidMove(new Move(-1, 0), MOVE)).isTrue();
        assertThat(queen.isValidMove(new Move(0, 3), MOVE)).isTrue();
        assertThat(queen.isValidMove(new Move(0, -1), MOVE)).isTrue();
        assertThat(queen.isValidMove(new Move(1, -1), MOVE)).isTrue();
        assertThat(queen.isValidMove(new Move(3, 3), MOVE)).isTrue();
        assertThat(queen.isValidMove(new Move(-1, 1), MOVE)).isTrue();
        assertThat(queen.isValidMove(new Move(-3, -3), MOVE)).isTrue();
    }

    @DisplayName("자신의 수가 아닌 움직임을 할 수 없다.")
    @Test
    void canNotMove() {
        Queen queen = new Queen(WHITE);

        assertThat(queen.isValidMove(new Move(2, 1), MOVE)).isFalse();
    }
}
