package chess.domain.piece.movepattern;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.board.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class WhitePawnMovePatternTest {

    @DisplayName("1칸 앞으로 이동하는 경우")
    @Test
    void testOne() {
        AbstractPawnMovePattern pattern = new WhitePawnMovePattern();
        Position src = Position.of("a2");
        Position dest = Position.of("a3");

        assertThat(pattern.canMove(src, dest)).isTrue();
    }

    @DisplayName("첫 번째 이동일 때 2칸 앞으로 이동하는 경우")
    @Test
    void testTwo() {
        AbstractPawnMovePattern pattern = new WhitePawnMovePattern();
        Position src = Position.of("a2");
        Position dest = Position.of("a4");

        assertThat(pattern.canMove(src, dest)).isTrue();
    }

    @DisplayName("첫 번째 이동이 아닌데 두 칸 움직이는 경우 false")
    @Test
    void testThree() {
        AbstractPawnMovePattern pattern = new WhitePawnMovePattern();
        Position src = Position.of("a3");
        Position dest = Position.of("a5");

        assertThat(pattern.canMove(src, dest)).isFalse();
    }

    @DisplayName("뒤로 이동할 수 없다.")
    @Test
    void testBack() {
        AbstractPawnMovePattern pattern = new WhitePawnMovePattern();
        Position src = Position.of("a2");
        Position dest = Position.of("a1");

        assertThat(pattern.canMove(src, dest)).isFalse();
    }
}
