package chess.domain.command;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.ChessGame;
import chess.domain.board.Board;
import chess.domain.board.Position;
import chess.domain.piece.Pawn;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class CommandStateTest {

    @Test
    void test() {
        String input = "start";
        CommandState start = ChessReady.startCommand(input);

        assertThat(start).isInstanceOf(Start.class);
    }

    @Test
    void testIsStart() {
        String input = "start";
        CommandState start = ChessReady.startCommand(input);

        assertThat(start.isStart()).isTrue();
    }

    @Test
    void testEnd() {
        String input = "end";
        CommandState end = ChessReady.startCommand(input);

        assertThat(end).isInstanceOf(End.class);
    }

    @Test
    void testEndIsStart() {
        String input = "end";
        CommandState end = ChessReady.startCommand(input);

        assertThat(end.isStart()).isFalse();
    }

    @Test
    void testInvalidInput() {
        String input = "unknown";
        Assertions.assertThatThrownBy(() -> ChessReady.startCommand(input))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void moveOnlyStartFromStart() {
        String input = "start";
        CommandState start = ChessReady.startCommand(input);
        ChessGame chessGame = new ChessGame(start);

        String moveInput = "move a2 a3";
        chessGame.execute(moveInput);

        Board board = chessGame.getBoard();

        assertThat(board.findPieceBy(Position.valueOf("a2"))).isEmpty();
        assertThat(board.findPieceBy(Position.valueOf("a3")).get()).isInstanceOf(Pawn.class);
    }

    @Test
    void moveOnlyStartFromStartFail() {
        String input = "start";
        CommandState start = ChessReady.startCommand(input);
        ChessGame chessGame = new ChessGame(start);

        String moveInput = "move a7 a6";

        assertThatThrownBy(() -> chessGame.execute(moveInput))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void startToEnd() {
        String input = "start";
        CommandState start = ChessReady.startCommand(input);
        ChessGame chessGame = new ChessGame(start);

        String endInput = "end";
        chessGame.execute(endInput);

        assertThat(chessGame.isFinished()).isTrue();
    }

    @Test
    void startToInvalidInput() {
        String input = "start";
        CommandState start = ChessReady.startCommand(input);
        ChessGame chessGame = new ChessGame(start);

        String unknown = "unknown";
        assertThatThrownBy(() -> chessGame.execute(unknown))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void endToCommand() {
        String input = "end";
        CommandState end = ChessReady.startCommand(input);

        String start = "start";
        assertThatThrownBy(() -> end.execute(start))
                .isInstanceOf(UnsupportedOperationException.class);
    }
}
