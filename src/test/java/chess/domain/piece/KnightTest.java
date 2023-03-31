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

public class KnightTest extends AbstractTestFixture {

    @DisplayName("한 방향으로 한 칸, 그리고 그 방향의 양 대각선 방향 중 한 방향으로 움직일 수 있다")
    @Test
    void canMove() {
        Knight knight = new Knight(Team.WHITE);

        assertThat(knight.hasMove(createMove(LEFT, LEFT, UP))).isTrue();
        assertThat(knight.hasMove(createMove(LEFT, UP, UP))).isTrue();
        assertThat(knight.hasMove(createMove(RIGHT, RIGHT, UP))).isTrue();
        assertThat(knight.hasMove(createMove(RIGHT, UP, UP))).isTrue();
        assertThat(knight.hasMove(createMove(RIGHT, RIGHT, DOWN))).isTrue();
        assertThat(knight.hasMove(createMove(RIGHT, DOWN, DOWN))).isTrue();
        assertThat(knight.hasMove(createMove(LEFT, LEFT, DOWN))).isTrue();
        assertThat(knight.hasMove(createMove(LEFT, DOWN, DOWN))).isTrue();
    }

    @DisplayName("자신의 수가 아닌 움직임을 할 수 없다.")
    @Test
    void canNotMove() {
        Knight knight = new Knight(Team.WHITE);

        assertThat(knight.hasMove(createMove(LEFT, LEFT, LEFT))).isFalse();
        assertThat(knight.hasMove(createMove(RIGHT, RIGHT))).isFalse();
        assertThat(knight.hasMove(createMove(UP, UP))).isFalse();
        assertThat(knight.hasMove(createMove(DOWN))).isFalse();
        assertThat(knight.hasMove(createMove(UP, RIGHT, UP, RIGHT, UP, RIGHT))).isFalse();
        assertThat(knight.hasMove(createMove(UP, LEFT))).isFalse();
        assertThat(knight.hasMove(createMove(DOWN, RIGHT))).isFalse();
        assertThat(knight.hasMove(createMove(DOWN, LEFT))).isFalse();
    }

    @DisplayName("한 단위 이상 움직일 수 없다")
    @Test
    void canNotMove_MoreThanOneUnit() {
        Knight knight = new Knight(Team.WHITE);

        assertThat(knight.hasMove(createMove(LEFT, LEFT, LEFT, LEFT, UP, UP))).isFalse();
    }

    @DisplayName("기본점수가 2.5점이다")
    @Test
    void scoreIsTwoAndHalf() {
        Knight knight = new Knight(Team.WHITE);

        assertThat(knight.score()).isEqualTo(2.5);
    }
}
