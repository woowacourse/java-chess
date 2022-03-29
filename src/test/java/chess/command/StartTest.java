package chess.command;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.board.Board;
import chess.domain.board.strategy.CreateCompleteBoardStrategy;
import chess.domain.state.ChessState;
import chess.domain.state.Finished;
import chess.domain.state.Ready;
import chess.domain.state.WhiteRunning;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class StartTest {

    @DisplayName("Ready 상태가 아니면 실행할 수 없다.")
    @Test
    void cannot_Execute() {
        Board board = new Board(new CreateCompleteBoardStrategy());
        ChessState finished = new Finished(board);
        Command command = new Start();

        assertThatThrownBy(() -> command.execute(finished))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("현재 실행할 수 없는 명령입니다.");
    }

    @DisplayName("Ready 상태면 실행시킬 수 있다.")
    @Test
    void start() {
        ChessState ready = new Ready();
        Command command = new Start();

        ChessState actual = command.execute(ready);

        assertThat(actual).isInstanceOf(WhiteRunning.class);
    }
}
