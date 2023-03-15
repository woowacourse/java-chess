package chess.domain.piece;

import static chess.domain.Direction.DOWN;
import static chess.domain.Direction.LEFT;
import static chess.domain.Direction.RIGHT;
import static chess.domain.Direction.UP;
import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.AbstractTestFixture;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class QueenTest extends AbstractTestFixture {

    @DisplayName("가로/세로/대각선 여러 칸 움직일 수 있다.")
    @Test
    void canMove_HorizontalVerticalDiagonal_Infinite() {
        Queen queen = Queen.from(true);

        assertThat(queen.canMove(createMove(LEFT, LEFT, LEFT))).isTrue();
        assertThat(queen.canMove(createMove(RIGHT, RIGHT))).isTrue();
        assertThat(queen.canMove(createMove(UP, UP))).isTrue();
        assertThat(queen.canMove(createMove(DOWN))).isTrue();
        assertThat(queen.canMove(createMove(UP, RIGHT, UP, RIGHT, UP, RIGHT))).isTrue();
        assertThat(queen.canMove(createMove(UP, LEFT))).isTrue();
        assertThat(queen.canMove(createMove(DOWN, RIGHT))).isTrue();
        assertThat(queen.canMove(createMove(DOWN, LEFT))).isTrue();
    }

    @DisplayName("자신의 수가 아닌 움직임을 할 수 없다.")
    @Test
    void canNotMove() {
        Queen queen = Queen.from(true);

        assertThat(queen.canMove(createMove(LEFT, LEFT, UP))).isFalse();
    }
}
