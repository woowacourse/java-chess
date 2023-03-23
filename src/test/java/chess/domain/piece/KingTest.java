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

public class KingTest extends AbstractTestFixture {

    @DisplayName("가로/세로/대각선 한 칸 움직일 수 있다.")
    @Test
    void canMove_HorizontalVerticalDiagonal_Once() {
        King king = new King(Team.WHITE);

        assertThat(king.hasMove(createMove(LEFT))).isTrue();
        assertThat(king.hasMove(createMove(RIGHT))).isTrue();
        assertThat(king.hasMove(createMove(UP))).isTrue();
        assertThat(king.hasMove(createMove(DOWN))).isTrue();
        assertThat(king.hasMove(createMove(UP, RIGHT))).isTrue();
        assertThat(king.hasMove(createMove(UP, LEFT))).isTrue();
        assertThat(king.hasMove(createMove(DOWN, RIGHT))).isTrue();
        assertThat(king.hasMove(createMove(DOWN, LEFT))).isTrue();
    }

    @DisplayName("가로/세로/대각선 여러 칸 움직일 수 없다.")
    @Test
    void canNotMove_HorizontalVerticalDiagonal_Twice() {
        King king = new King(Team.WHITE);

        assertThat(king.hasMove(createMove(LEFT, LEFT))).isFalse();
        assertThat(king.hasMove(createMove(UP, RIGHT, UP, RIGHT))).isFalse();
    }

    @DisplayName("자신의 수가 아닌 움직임을 할 수 없다.")
    @Test
    void canNotMove() {
        King king = new King(Team.WHITE);

        assertThat(king.hasMove(createMove(LEFT, LEFT, UP))).isFalse();
    }
}
