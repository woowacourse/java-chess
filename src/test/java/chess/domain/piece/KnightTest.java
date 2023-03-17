package chess.domain.piece;


import static chess.domain.board.MoveType.MOVE;
import static chess.domain.piece.Color.WHITE;
import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.position.Move;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class KnightTest {

    @DisplayName("한 방향으로 한 칸, 그리고 그 방향의 양 대각선 방향 중 한 방향으로 움직일 수 있다")
    @Test
    void canMove() {
        Knight knight = new Knight(WHITE);

        assertThat(knight.isValidMove(new Move(1, 2), MOVE)).isTrue();
        assertThat(knight.isValidMove(new Move(1, -2), MOVE)).isTrue();
        assertThat(knight.isValidMove(new Move(-1, 2), MOVE)).isTrue();
        assertThat(knight.isValidMove(new Move(-1, -2), MOVE)).isTrue();
        assertThat(knight.isValidMove(new Move(2, 1), MOVE)).isTrue();
        assertThat(knight.isValidMove(new Move(2, -1), MOVE)).isTrue();
        assertThat(knight.isValidMove(new Move(-2, 1), MOVE)).isTrue();
        assertThat(knight.isValidMove(new Move(-2, -1), MOVE)).isTrue();
    }

    @DisplayName("한 단위 이상 움직일 수 없다")
    @Test
    void canNotMove_MoreThanOneUnit() {
        Knight knight = new Knight(WHITE);

        assertThat(knight.isValidMove(new Move(2, 4), MOVE)).isFalse();
    }

    @DisplayName("자신의 수가 아닌 움직임을 할 수 없다.")
    @Test
    void canNotMove() {
        Knight knight = new Knight(WHITE);

        assertThat(knight.isValidMove(new Move(3, 1), MOVE)).isFalse();
        assertThat(knight.isValidMove(new Move(3, 0), MOVE)).isFalse();
        assertThat(knight.isValidMove(new Move(1, 1), MOVE)).isFalse();
    }
}
