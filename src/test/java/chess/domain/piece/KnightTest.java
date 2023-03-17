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

public class KnightTest {

    @DisplayName("한 방향으로 한 칸, 그리고 그 방향의 양 대각선 방향 중 한 방향으로 움직일 수 있다")
    @Test
    void canMove() {
        Knight knight = new Knight(WHITE);

        assertThat(knight.isValidMove(new Move(LEFT, LEFT, UP), MOVE)).isTrue();
        assertThat(knight.isValidMove(new Move(LEFT, UP, UP), MOVE)).isTrue();
        assertThat(knight.isValidMove(new Move(RIGHT, RIGHT, UP), MOVE)).isTrue();
        assertThat(knight.isValidMove(new Move(RIGHT, UP, UP), MOVE)).isTrue();
        assertThat(knight.isValidMove(new Move(RIGHT, RIGHT, DOWN), MOVE)).isTrue();
        assertThat(knight.isValidMove(new Move(RIGHT, DOWN, DOWN), MOVE)).isTrue();
        assertThat(knight.isValidMove(new Move(LEFT, LEFT, DOWN), MOVE)).isTrue();
        assertThat(knight.isValidMove(new Move(LEFT, DOWN, DOWN), MOVE)).isTrue();
    }

    @DisplayName("자신의 수가 아닌 움직임을 할 수 없다.")
    @Test
    void canNotMove() {
        Knight knight = new Knight(WHITE);

        assertThat(knight.isValidMove(new Move(LEFT, LEFT, LEFT), MOVE)).isFalse();
        assertThat(knight.isValidMove(new Move(RIGHT, RIGHT), MOVE)).isFalse();
        assertThat(knight.isValidMove(new Move(UP, UP), MOVE)).isFalse();
        assertThat(knight.isValidMove(new Move(DOWN), MOVE)).isFalse();
        assertThat(knight.isValidMove(new Move(UP, RIGHT, UP, RIGHT, UP, RIGHT), MOVE)).isFalse();
        assertThat(knight.isValidMove(new Move(UP, LEFT), MOVE)).isFalse();
        assertThat(knight.isValidMove(new Move(DOWN, RIGHT), MOVE)).isFalse();
        assertThat(knight.isValidMove(new Move(DOWN, LEFT), MOVE)).isFalse();
    }

    @DisplayName("한 단위 이상 움직일 수 없다")
    @Test
    void canNotMove_MoreThanOneUnit() {
        Knight knight = new Knight(WHITE);

        assertThat(knight.isValidMove(new Move(LEFT, LEFT, LEFT, LEFT, UP, UP), MOVE)).isFalse();
    }
}
