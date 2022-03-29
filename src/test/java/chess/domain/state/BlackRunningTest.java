package chess.domain.state;

import static org.assertj.core.api.Assertions.assertThat;

import chess.Command;
import chess.domain.board.Board;
import chess.domain.board.strategy.CreateCompleteBoardStrategy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BlackRunningTest {

    @DisplayName("BlackRunning 상태에서 move 하면 WhiteRunning이 된다.")
    @Test
    void move() {
        Board board = new Board(new CreateCompleteBoardStrategy());
        ChessState running = new BlackRunning(board);

        ChessState actual = running.execute(Command.MOVE, "a7", "a6");

        assertThat(actual).isInstanceOf(WhiteRunning.class);
    }
}
