package chess.domain.state;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.CachedPosition;
import chess.domain.board.Board;
import chess.domain.board.strategy.CreateCompleteBoardStrategy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ReadyTest {

    @DisplayName("move 명령을 실행하면 예외를 반환한다.")
    @Test
    void cannot_Move() {
        ChessState ready = new Ready(new Board(new CreateCompleteBoardStrategy()));

        assertThatThrownBy(() -> ready.move(CachedPosition.a1, CachedPosition.a2))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("현재 실행할 수 없는 명령입니다.");
    }

    @DisplayName("end 명령을 실행하면 예외를 반환한다.")
    @Test
    void cannot_End() {
        ChessState ready = new Ready(new Board(new CreateCompleteBoardStrategy()));

        assertThatThrownBy(ready::end)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("현재 실행할 수 없는 명령입니다.");
    }

    @DisplayName("start 명령을 실행하면 Running 상태를 반환한다.")
    @Test
    void start() {
        ChessState ready = new Ready(new Board(new CreateCompleteBoardStrategy()));

        ChessState actual = ready.start();

        assertThat(actual).isInstanceOf(Running.class);
    }

    @DisplayName("ready 상태에서는 보드 정보를 반환할 수 없다.")
    @Test
    void cannot_Return_Pieces() {
        ChessState ready = new Ready(new Board(new CreateCompleteBoardStrategy()));

        assertThatThrownBy(ready::getPieces)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("현재 실행할 수 없는 명령입니다.");
    }

    @DisplayName("ready 상태에서는 Status를 만들 수 없다.")
    @Test
    void cannot_Create_Status() {
        ChessState ready = new Ready(new Board(new CreateCompleteBoardStrategy()));

        assertThatThrownBy(ready::createStatus)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("현재 실행할 수 없는 명령입니다.");
    }
}
