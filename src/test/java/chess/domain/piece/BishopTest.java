package chess.domain.piece;


import static chess.domain.board.MoveType.MOVE;
import static chess.domain.piece.Color.WHITE;
import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.position.Move;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BishopTest {

    @DisplayName("대각선 여러 칸 움직일 수 있다.")
    @Test
    void canMove_Diagonal_Infinite() {
        Bishop bishop = new Bishop(WHITE);

        assertThat(bishop.isValidMove(new Move(1, 1), MOVE)).isTrue();
        assertThat(bishop.isValidMove(new Move(-2, 2), MOVE)).isTrue();
        assertThat(bishop.isValidMove(new Move(-3, -3), MOVE)).isTrue();
        assertThat(bishop.isValidMove(new Move(4, -4), MOVE)).isTrue();
    }

    @DisplayName("자신의 수가 아닌 움직임을 할 수 없다.")
    @Test
    void canNotMove() {
        Bishop bishop = new Bishop(WHITE);

        assertThat(bishop.isValidMove(new Move(1, 0), MOVE)).isFalse();
        assertThat(bishop.isValidMove(new Move(-2, 0), MOVE)).isFalse();
        assertThat(bishop.isValidMove(new Move(0, 3), MOVE)).isFalse();
        assertThat(bishop.isValidMove(new Move(1, -4), MOVE)).isFalse();
        assertThat(bishop.isValidMove(new Move(2, 1), MOVE)).isFalse();
        assertThat(bishop.isValidMove(new Move(1, -2), MOVE)).isFalse();
    }
}
