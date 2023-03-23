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

public class RookTest extends AbstractTestFixture {

    @DisplayName("가로/세로 여러 칸 움직일 수 있다.")
    @Test
    void canMove_HorizontalVertical_Infinite() {
        Rook rook = new Rook(Team.WHITE);

        assertThat(rook.hasMove(createMove(LEFT, LEFT, LEFT))).isTrue();
        assertThat(rook.hasMove(createMove(RIGHT, RIGHT))).isTrue();
        assertThat(rook.hasMove(createMove(UP, UP))).isTrue();
        assertThat(rook.hasMove(createMove(DOWN))).isTrue();
    }

    @DisplayName("자신의 수가 아닌 움직임을 할 수 없다.")
    @Test
    void canNotMove() {
        Rook rook = new Rook(Team.WHITE);

        assertThat(rook.hasMove(createMove(LEFT, LEFT, UP))).isFalse();
        assertThat(rook.hasMove(createMove(LEFT, UP))).isFalse();
        assertThat(rook.hasMove(createMove(RIGHT, RIGHT, DOWN, DOWN))).isFalse();
    }
}
