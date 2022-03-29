package chess.domain.state;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.Command;
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

        assertThatThrownBy(() -> running.execute(Command.START))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("현재 실행할 수 없는 명령입니다.");
    }

    @DisplayName("end 명령을 실행하면 End 상태를 반환한다.")
    @Test
    void end() {
        Board board = new Board(new CreateCompleteBoardStrategy());
        ChessState running = new WhiteRunning(board);

        ChessState actual = running.execute(Command.END);

        assertThat(actual).isInstanceOf(End.class);
    }

    @DisplayName("move 명령을 실행할 때 인수 개수가 잘못되면 예외를 반환한다.")
    @Test
    void invalid_Arguments_Count() {
        Board board = new Board(new CreateCompleteBoardStrategy());
        ChessState running = new WhiteRunning(board);

        assertThatThrownBy(() -> running.execute(Command.MOVE, "a1"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("올바르지 않은 이동 명령입니다.");
    }

    @DisplayName("move 명령을 실행할 때 위치 인수가 잘못되면 예외를 반환한다.")
    @Test
    void invalid_Position_Argument() {
        Board board = new Board(new CreateCompleteBoardStrategy());
        ChessState running = new WhiteRunning(board);

        assertThatThrownBy(() -> running.execute(Command.MOVE, "a11", "b11"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("올바르지 않은 이동 명령입니다.");
    }
}
