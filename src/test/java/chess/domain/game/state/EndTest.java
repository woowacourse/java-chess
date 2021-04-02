package chess.domain.game.state;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import chess.domain.board.Board;
import chess.domain.board.position.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class EndTest {

    private End end;

    @BeforeEach
    void setUp() {
        this.end = new End(new Board());
    }

    @Test
    @DisplayName("프로그램 종료 상태에서 start 명령시 예외 반환")
    void testStartException() {
        assertThatThrownBy(() -> this.end.start()).isInstanceOf(IllegalStateException.class);
    }

    @Test
    @DisplayName("프로그램 종료 상태에서 move 명령시 예외 반환")
    void testMoveException() {
        assertThatThrownBy(() -> this.end.moveIfValidColor(Position.of("a2"), Position.of("a4")))
            .isInstanceOf(IllegalStateException.class);
    }

    @Test
    @DisplayName("프로그램 종료 상태에서 passTurn 예외 반환")
    void testPassTurnException() {
        assertThatThrownBy(() -> this.end.passTurn()).isInstanceOf(IllegalStateException.class);
    }

    @Test
    @DisplayName("프로그램 종료 상태에서 board 명령시 예외 반환")
    void testBoardException() {
        assertThatThrownBy(() -> this.end.board()).isInstanceOf(IllegalStateException.class);
    }

    @Test
    @DisplayName("프로그램 종료 상태에서 winner 명령시 예외 반환")
    void testWinnerException() {
        assertThatThrownBy(() -> this.end.winner()).isInstanceOf(IllegalStateException.class);
    }

    @Test
    @DisplayName("프로그램 종료 상태에서 end 명령시 예외 반환")
    void testEndException() {
        assertThatThrownBy(() -> this.end.end()).isInstanceOf(IllegalStateException.class);
    }

    @Test
    @DisplayName("프로그램 종료 상태에서 isInit 명령시 false 반환")
    void testIsInitFalse() {
        assertThat(this.end.isInit()).isFalse();
    }

    @Test
    @DisplayName("프로그램 종료 상태에서 isRunning 명령시 false 반환")
    void testIsRunningFalse() {
        assertThat(this.end.isRunning()).isFalse();
    }

    @Test
    @DisplayName("프로그램 종료 상태에서 isFinished 명령시 false 반환")
    void testIsFinishedFalse() {
        assertThat(this.end.isFinished()).isFalse();
    }

    @Test
    @DisplayName("프로그램 종료 상태에서 isNotEnd 명령시 false 반환")
    void testIsNotEndFalse() {
        assertThat(this.end.isNotEnd()).isFalse();
    }

    @Test
    @DisplayName("프로그램 종료 상태에서 movablePath 명령시 예외 반환")
    void testMovablePathException() {
        assertThatThrownBy(() -> this.end.movablePath(Position.of("a2")))
            .isInstanceOf(IllegalStateException.class);
    }

    @Test
    @DisplayName("프로그램 종료에서 state 명령시 프로그램 종료 반환")
    void testState() {
        assertThat(this.end.state()).isEqualTo("종료");
    }

}
