package chess.domain.piece.movepattern;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.board.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BlackPawnMovePatternTest {

    @DisplayName("1칸 앞으로 이동하는 경우")
    @Test
    void testOne() {
        MovePattern pattern = new BlackPawnMovePattern();
        Position source = Position.of("a7");
        Position destination = Position.of("a6");

        assertThat(pattern.canMove(source, destination)).isTrue();
    }

    @DisplayName("첫 번째 이동일 때 2칸 앞으로 이동하는 경우")
    @Test
    void testTwo() {
        MovePattern pattern = new BlackPawnMovePattern();
        Position source = Position.of("a7");
        Position destination = Position.of("a5");

        assertThat(pattern.canMove(source, destination)).isTrue();
    }

    @DisplayName("첫 번째 이동이 아닌데 두 칸 움직이는 경우 false")
    @Test
    void testThree() {
        MovePattern pattern = new BlackPawnMovePattern();
        Position source = Position.of("a6");
        Position destination = Position.of("a4");

        assertThat(pattern.canMove(source, destination)).isFalse();
    }

    @DisplayName("뒤로 이동할 수 없다.")
    @Test
    void testBack() {
        MovePattern pattern = new BlackPawnMovePattern();
        Position source = Position.of("a7");
        Position destination = Position.of("a8");

        assertThat(pattern.canMove(source, destination)).isFalse();
    }
}
