package chess.domain.piece;

import static chess.domain.move.Direction.DOWN;
import static chess.domain.move.Direction.LEFT;
import static chess.domain.move.Direction.RIGHT;
import static chess.domain.move.Direction.UP;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chess.domain.AbstractTestFixture;
import chess.domain.game.Team;

public class QueenTest extends AbstractTestFixture {

    @DisplayName("가로/세로/대각선 여러 칸 움직일 수 있다.")
    @Test
    void canMove_HorizontalVerticalDiagonal_Infinite() {
        Queen queen = new Queen(Team.WHITE);

        assertThat(queen.hasMove(createMove(LEFT, LEFT, LEFT))).isTrue();
        assertThat(queen.hasMove(createMove(RIGHT, RIGHT))).isTrue();
        assertThat(queen.hasMove(createMove(UP, UP))).isTrue();
        assertThat(queen.hasMove(createMove(DOWN))).isTrue();
        assertThat(queen.hasMove(createMove(UP, RIGHT, UP, RIGHT, UP, RIGHT))).isTrue();
        assertThat(queen.hasMove(createMove(UP, LEFT))).isTrue();
        assertThat(queen.hasMove(createMove(DOWN, RIGHT))).isTrue();
        assertThat(queen.hasMove(createMove(DOWN, LEFT))).isTrue();
    }

    @DisplayName("자신의 수가 아닌 움직임을 할 수 없다.")
    @Test
    void canNotMove() {
        Queen queen = new Queen(Team.WHITE);

        assertThat(queen.hasMove(createMove(LEFT, LEFT, UP))).isFalse();
    }
}
