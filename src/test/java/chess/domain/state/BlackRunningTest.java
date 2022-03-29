package chess.domain.state;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.CachedPosition;
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

        ChessState actual = running.move(CachedPosition.a7, CachedPosition.a6);

        assertThat(actual).isInstanceOf(WhiteRunning.class);
    }
}
