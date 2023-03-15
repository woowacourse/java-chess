package chess.domain.piece;


import static chess.domain.Direction.DOWN;
import static chess.domain.Direction.LEFT;
import static chess.domain.Direction.RIGHT;
import static chess.domain.Direction.UP;
import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.AbstractTestFixture;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class KnightTest extends AbstractTestFixture {

    @DisplayName("한 방향으로 한 칸, 그리고 그 방향의 양 대각선 방향 중 한 방향으로 움직일 수 있다")
    @Test
    void canMove() {
        Knight knight = Knight.from(true);

        assertThat(knight.canMove(createMove(LEFT, LEFT, UP))).isTrue();
        assertThat(knight.canMove(createMove(LEFT, UP, UP))).isTrue();
        assertThat(knight.canMove(createMove(RIGHT, RIGHT, UP))).isTrue();
        assertThat(knight.canMove(createMove(RIGHT, UP, UP))).isTrue();
        assertThat(knight.canMove(createMove(RIGHT, RIGHT, DOWN))).isTrue();
        assertThat(knight.canMove(createMove(RIGHT, DOWN, DOWN))).isTrue();
        assertThat(knight.canMove(createMove(LEFT, LEFT, DOWN))).isTrue();
        assertThat(knight.canMove(createMove(LEFT, DOWN, DOWN))).isTrue();
    }

    @DisplayName("자신의 수가 아닌 움직임을 할 수 없다.")
    @Test
    void canNotMove() {
        Knight knight = Knight.from(true);

        assertThat(knight.canMove(createMove(LEFT, LEFT, LEFT))).isFalse();
        assertThat(knight.canMove(createMove(RIGHT, RIGHT))).isFalse();
        assertThat(knight.canMove(createMove(UP, UP))).isFalse();
        assertThat(knight.canMove(createMove(DOWN))).isFalse();
        assertThat(knight.canMove(createMove(UP, RIGHT, UP, RIGHT, UP, RIGHT))).isFalse();
        assertThat(knight.canMove(createMove(UP, LEFT))).isFalse();
        assertThat(knight.canMove(createMove(DOWN, RIGHT))).isFalse();
        assertThat(knight.canMove(createMove(DOWN, LEFT))).isFalse();
    }

    @DisplayName("한 단위 이상 움직일 수 없다")
    @Test
    void canNotMove_MoreThanOneUnit() {
        Knight knight = Knight.from(true);

        assertThat(knight.canMove(createMove(LEFT, LEFT, LEFT, LEFT, UP, UP))).isFalse();
    }
}
