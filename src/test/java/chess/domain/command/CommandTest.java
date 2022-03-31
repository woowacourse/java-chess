package chess.domain.command;

import chess.domain.position.Position;
import chess.domain.board.Pawn;
import chess.domain.board.Piece;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class CommandTest {

    @Test
    void test() {
        String input = "start";
        Command start = Command.of(input);

        assertThat(start).isInstanceOf(Start.class);
    }

    @Test
    void testIsStart() {
        String input = "start";
        Command start = Command.of(input);

        assertThat(start.isStart()).isTrue();
    }

    @Test
    void testEnd() {
        String input = "end";
        Command end = Command.of(input);

        assertThat(end).isInstanceOf(End.class);
    }

    @Test
    void testEndIsStart() {
        String input = "end";
        Command end = Command.of(input);

        assertThat(end.isStart()).isFalse();
    }

    @Test
    void testInvalidInput() {
        String input = "unknown";
        Assertions.assertThatThrownBy(() -> Command.of(input))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void moveOnlyStartFromStart() {
        String input = "start";
        Command start = Command.of(input);

        String moveInput = "move a2 a3";
        start = start.execute(moveInput);

        Map<Position, Piece> board = start.getBoard();

        assertThat(board.get(Position.of("a2"))).isNull();
        assertThat(board.get(Position.of("a3"))).isInstanceOf(Pawn.class);
    }

    @Test
    void moveOnlyStartFromStartFail() {
        String input = "start";
        Command start = Command.of(input);

        String moveInput = "move a7 a6";

        assertThatThrownBy(() -> start.execute(moveInput))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void startToEnd() {
        String input = "start";
        Command start = Command.of(input);

        String endInput = "end";
        start = start.execute(endInput);

        assertThat(start).isInstanceOf(End.class);
    }

    @Test
    void startToInvalidInput() {
        String input = "start";
        Command start = Command.of(input);

        String unknown = "unknown";
        assertThatThrownBy(() -> start.execute(unknown))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void endToCommand() {
        String input = "end";
        Command end = Command.of(input);

        String start = "start";
        assertThatThrownBy(() -> end.execute(start))
                .isInstanceOf(UnsupportedOperationException.class);
    }
}
