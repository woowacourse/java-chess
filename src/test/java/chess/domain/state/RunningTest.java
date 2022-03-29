package chess.domain.state;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.board.Board;
import chess.domain.board.strategy.CreateCompleteBoardStrategy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class RunningTest {

    @DisplayName("start 명령을 실행하면 예외를 반환한다.")
    @Test
    void cannot_Invalid_Command() {
        Board board = new Board(new CreateCompleteBoardStrategy());
        ChessState running = new WhiteRunning(board);

        assertThatThrownBy(running::start)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("현재 실행할 수 없는 명령입니다.");
    }

    @DisplayName("end 명령을 실행하면 End 상태를 반환한다.")
    @Test
    void end() {
        Board board = new Board(new CreateCompleteBoardStrategy());
        ChessState running = new WhiteRunning(board);

        ChessState actual = running.end();

        assertThat(actual).isInstanceOf(Finished.class);
    }
}
