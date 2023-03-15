package chess.domain.piece;

import static chess.domain.Direction.DOWN;
import static chess.domain.Direction.LEFT;
import static chess.domain.Direction.RIGHT;
import static chess.domain.Direction.UP;
import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.AbstractTestFixture;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class KingTest extends AbstractTestFixture {

    @DisplayName("가로/세로/대각선 한 칸 움직일 수 있다.")
    @Test
    void canMove_HorizontalVerticalDiagonal_Once() {
        King king = King.from(true);

        assertThat(king.canMove(createMove(LEFT))).isTrue();
        assertThat(king.canMove(createMove(RIGHT))).isTrue();
        assertThat(king.canMove(createMove(UP))).isTrue();
        assertThat(king.canMove(createMove(DOWN))).isTrue();
        assertThat(king.canMove(createMove(UP, RIGHT))).isTrue();
        assertThat(king.canMove(createMove(UP, LEFT))).isTrue();
        assertThat(king.canMove(createMove(DOWN, RIGHT))).isTrue();
        assertThat(king.canMove(createMove(DOWN, LEFT))).isTrue();
    }

    @DisplayName("가로/세로/대각선 여러 칸 움직일 수 없다.")
    @Test
    void canNotMove_HorizontalVerticalDiagonal_Twice() {
        King king = King.from(true);

        assertThat(king.canMove(createMove(LEFT, LEFT))).isFalse();
        assertThat(king.canMove(createMove(UP, RIGHT, UP, RIGHT))).isFalse();
    }

    @DisplayName("자신의 수가 아닌 움직임을 할 수 없다.")
    @Test
    void canNotMove() {
        King king = King.from(true);

        assertThat(king.canMove(createMove(LEFT, LEFT, UP))).isFalse();
    }
}
