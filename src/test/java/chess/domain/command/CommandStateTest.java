package chess.domain.command;

import chess.domain.board.Board;
import chess.domain.board.Position;
import chess.domain.piece.Pawn;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class CommandStateTest {

    @Test
    void test() {
        String input = "start";
        CommandState start = CommandState.of(input);

        assertThat(start).isInstanceOf(Start.class);
    }

    @Test
    void testIsStart() {
        String input = "start";
        CommandState start = CommandState.of(input);

        assertThat(start.isStart()).isTrue();
    }

    @Test
    void testEnd() {
        String input = "end";
        CommandState end = CommandState.of(input);

        assertThat(end).isInstanceOf(End.class);
    }

    @Test
    void testEndIsStart() {
        String input = "end";
        CommandState end = CommandState.of(input);

        assertThat(end.isStart()).isFalse();
    }

    @Test
    void testInvalidInput() {
        String input = "unknown";
        Assertions.assertThatThrownBy(() -> CommandState.of(input))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void moveOnlyStartFromStart() {
        String input = "start";
        CommandState start = CommandState.of(input);

        String moveInput = "move a2 a3";
        start = start.execute(moveInput);

        Board board = start.getBoard();

        assertThat(board.findPieceBy(Position.of("a2"))).isEmpty();
        assertThat(board.findPieceBy(Position.of("a3")).get()).isInstanceOf(Pawn.class);
    }

    @Test
    void moveOnlyStartFromStartFail() {
        String input = "start";
        CommandState start = CommandState.of(input);

        String moveInput = "move a7 a6";

        assertThatThrownBy(() -> start.execute(moveInput))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void startToEnd() {
        String input = "start";
        CommandState start = CommandState.of(input);

        String endInput = "end";
        start = start.execute(endInput);

        assertThat(start).isInstanceOf(End.class);
    }

    @Test
    void startToInvalidInput() {
        String input = "start";
        CommandState start = CommandState.of(input);

        String unknown = "unknown";
        assertThatThrownBy(() -> start.execute(unknown))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void endToCommand() {
        String input = "end";
        CommandState end = CommandState.of(input);

        String start = "start";
        assertThatThrownBy(() -> end.execute(start))
                .isInstanceOf(UnsupportedOperationException.class);
    }
}
