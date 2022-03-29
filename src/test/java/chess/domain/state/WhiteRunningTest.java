package chess.domain.state;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.CachedPosition;
import chess.domain.board.Board;
import chess.domain.board.strategy.CreateCompleteBoardStrategy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class WhiteRunningTest {

    @DisplayName("WhiteRunning 상태에서 move 하면 BlackRunning이 된다.")
    @Test
    void move() {
        Board board = new Board(new CreateCompleteBoardStrategy());
        ChessState running = new WhiteRunning(board);

        ChessState actual = running.move(CachedPosition.a2, CachedPosition.a3);

        assertThat(actual).isInstanceOf(BlackRunning.class);
    }
}
