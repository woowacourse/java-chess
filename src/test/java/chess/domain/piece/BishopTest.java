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

public class BishopTest extends AbstractTestFixture {

    @DisplayName("대각선 여러 칸 움직일 수 있다.")
    @Test
    void canMove_Diagonal_Infinite() {
        Bishop bishop = new Bishop(Team.WHITE);

        assertThat(bishop.hasMove(createMove(UP, RIGHT, UP, RIGHT, UP, RIGHT))).isTrue();
        assertThat(bishop.hasMove(createMove(UP, LEFT))).isTrue();
        assertThat(bishop.hasMove(createMove(DOWN, RIGHT))).isTrue();
        assertThat(bishop.hasMove(createMove(DOWN, LEFT))).isTrue();
    }

    @DisplayName("자신의 수가 아닌 움직임을 할 수 없다.")
    @Test
    void canNotMove() {
        Bishop bishop = new Bishop(Team.WHITE);

        assertThat(bishop.hasMove(createMove(LEFT, LEFT, LEFT))).isFalse();
        assertThat(bishop.hasMove(createMove(RIGHT, RIGHT))).isFalse();
        assertThat(bishop.hasMove(createMove(UP, UP))).isFalse();
        assertThat(bishop.hasMove(createMove(DOWN))).isFalse();
        assertThat(bishop.hasMove(createMove(LEFT, LEFT, UP))).isFalse();
    }

    @DisplayName("기본점수가 3점이다")
    @Test
    void scoreIsThree() {
        Bishop bishop = new Bishop(Team.WHITE);

        assertThat(bishop.score()).isEqualTo(3);
    }
}
