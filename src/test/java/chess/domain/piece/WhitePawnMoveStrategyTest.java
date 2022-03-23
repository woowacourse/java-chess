package chess.domain.piece;

import chess.domain.board.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class WhitePawnMoveStrategyTest {

    @DisplayName("1칸 앞으로 이동하는 경우")
    @Test
    void testOne() {
        MoveStrategy strategy = new WhitePawnMoveStrategy();
        Position src = Position.of("a2");
        Position dest = Position.of("a3");

        assertThat(strategy.canMove(src, dest)).isTrue();
    }

    @DisplayName("첫 번째 이동일 때 2칸 앞으로 이동하는 경우")
    @Test
    void testTwo() {
        MoveStrategy strategy = new WhitePawnMoveStrategy();
        Position src = Position.of("a2");
        Position dest = Position.of("a4");

        assertThat(strategy.canMove(src, dest)).isTrue();
    }

    @DisplayName("첫 번째 이동이 아닌데 두 칸 움직이는 경우 false")
    @Test
    void testThree() {
        MoveStrategy strategy = new WhitePawnMoveStrategy();
        Position src = Position.of("a3");
        Position dest = Position.of("a5");

        assertThat(strategy.canMove(src, dest)).isFalse();
    }

    @DisplayName("뒤로 이동할 수 없다.")
    @Test
    void testBack() {
        MoveStrategy strategy = new WhitePawnMoveStrategy();
        Position src = Position.of("a2");
        Position dest = Position.of("a1");

        assertThat(strategy.canMove(src, dest)).isFalse();
    }
}
