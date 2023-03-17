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

public class KingTest {

    @DisplayName("가로/세로/대각선 한 칸 움직일 수 있다.")
    @Test
    void canMove_HorizontalVerticalDiagonal_Once() {
        King king = new King(WHITE);

        assertThat(king.hasMove(new Move(LEFT))).isTrue();
        assertThat(king.hasMove(new Move(RIGHT))).isTrue();
        assertThat(king.hasMove(new Move(UP))).isTrue();
        assertThat(king.hasMove(new Move(DOWN))).isTrue();
        assertThat(king.hasMove(new Move(UP, RIGHT))).isTrue();
        assertThat(king.hasMove(new Move(UP, LEFT))).isTrue();
        assertThat(king.hasMove(new Move(DOWN, RIGHT))).isTrue();
        assertThat(king.hasMove(new Move(DOWN, LEFT))).isTrue();
    }

    @DisplayName("가로/세로/대각선 여러 칸 움직일 수 없다.")
    @Test
    void canNotMove_HorizontalVerticalDiagonal_Twice() {
        King king = new King(WHITE);

        assertThat(king.hasMove(new Move(LEFT, LEFT))).isFalse();
        assertThat(king.hasMove(new Move(UP, RIGHT, UP, RIGHT))).isFalse();
    }

    @DisplayName("자신의 수가 아닌 움직임을 할 수 없다.")
    @Test
    void canNotMove() {
        King king = new King(WHITE);

        assertThat(king.hasMove(new Move(LEFT, LEFT, UP))).isFalse();
    }
}
