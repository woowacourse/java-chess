package chess.command;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.CachedPosition;
import chess.domain.board.Board;
import chess.domain.board.strategy.CreateCompleteBoardStrategy;
import chess.domain.state.BlackRunning;
import chess.domain.state.ChessState;
import chess.domain.state.Finished;
import chess.domain.state.WhiteRunning;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MoveTest {

    @DisplayName("Running 상태가 아니면 예외를 반환한다.")
    @Test
    void cannot_Move() {
        Board board = new Board(new CreateCompleteBoardStrategy());
        ChessState finished = new Finished(board);
        Command command = new Move(CachedPosition.a1, CachedPosition.a2);

        assertThatThrownBy(() -> command.execute(finished))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("현재 실행할 수 없는 명령입니다.");
    }

    @DisplayName("Running 상태면 이동시킬 수 있다.")
    @Test
    void move() {
        Board board = new Board(new CreateCompleteBoardStrategy());
        ChessState running = new WhiteRunning(board);
        Command command = new Move(CachedPosition.a2, CachedPosition.a4);

        ChessState actual = command.execute(running);

        assertThat(actual).isInstanceOf(BlackRunning.class);
    }
}
