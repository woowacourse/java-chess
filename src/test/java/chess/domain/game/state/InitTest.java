package chess.domain.game.state;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import chess.domain.board.Board;
import chess.domain.board.position.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class InitTest {

    private Init init;

    @BeforeEach
    void setUp() {
        this.init = new Init(new Board());
    }

    @Test
    @DisplayName("초기 상태에서 start 명령시 화이트 턴 반환")
    void testStart() {
        assertThat(this.init.start()).isInstanceOf(WhiteTurn.class);
    }

    @Test
    @DisplayName("초기 상태에서 move 명령시 에러 반환")
    void testMoveException() {
        assertThatThrownBy(() -> this.init.moveIfValidColor(Position.of("c5"), Position.of("c6")))
            .isInstanceOf(IllegalStateException.class);
    }

    @Test
    @DisplayName("초기 상태에서 passTurn 명령시 에러 반환")
    void testPassTurnException() {
        assertThatThrownBy(() -> this.init.passTurn()).isInstanceOf(IllegalStateException.class);
    }

    @Test
    @DisplayName("초기 상태에서 board 명령시 에러 반환")
    void testBoardException() {
        assertThatThrownBy(() -> this.init.board()).isInstanceOf(IllegalStateException.class);
    }

    @Test
    @DisplayName("초기 상태에서 winner 명령시 에러 반환")
    void testWinnerException() {
        assertThatThrownBy(() -> this.init.winner()).isInstanceOf(IllegalStateException.class);
    }

    @Test
    @DisplayName("초기 상태에서 end 명령시 End 상태 반환")
    void testEnd() {
        assertThat(this.init.end()).isInstanceOf(End.class);
    }

    @Test
    @DisplayName("초기 상태에서 isInit 명령시 true 반환")
    void testIsInitTure() {
        assertThat(this.init.isInit()).isTrue();
    }

    @Test
    @DisplayName("초기 상태에서 isRunning 명령시 false 반환")
    void testIsRunningFalse() {
        assertThat(this.init.isRunning()).isFalse();
    }

    @Test
    @DisplayName("초기 상태에서 isFinished 명령시 false 반환")
    void testIsFinishedFalse() {
        assertThat(this.init.isFinished()).isFalse();
    }

    @Test
    @DisplayName("초기 상태에서 isNotEnd 명령시 true 반환")
    void testIsNotEndTrue() {
        assertThat(this.init.isNotEnd()).isTrue();
    }

    @Test
    @DisplayName("초기 상태에서 movablePath 명령시 예외 반환")
    void testMovablePathException() {
        assertThatThrownBy(() -> this.init.movablePath(Position.of("a2")))
            .isInstanceOf(IllegalStateException.class);
    }

    @Test
    @DisplayName("초기 상태에서 state 명령시 초기 상태 반환")
    void testState(){
        assertThat(this.init.state()).isInstanceOf(Init.class);
    }
}
