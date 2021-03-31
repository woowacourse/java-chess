package chess.domain.game.state;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import chess.domain.board.Board;
import chess.domain.board.position.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class WhiteWinTest {

    private WhiteWin whiteWin;

    @BeforeEach
    void setUp() {
        this.whiteWin = new WhiteWin(new Board());
    }

    @Test
    @DisplayName("백색 승리 상태에서 start 명령시 Init 반환")
    void testStart() {
        assertThat(this.whiteWin.start()).isInstanceOf(Init.class);
    }

    @Test
    @DisplayName("백색 승리 상태에서 move 명령시 예외 반")
    void testMoveException() {
        assertThatThrownBy(() ->
            this.whiteWin.moveIfValidColor(Position.of("a7"), Position.of("a5"))
        ).isInstanceOf(IllegalStateException.class);
    }

    @Test
    @DisplayName("백색 승리 상태에서 passTurn 명령시 예외 반환")
    void testPassTurnException() {
        assertThatThrownBy(() -> this.whiteWin.passTurn())
            .isInstanceOf(IllegalStateException.class);
    }

    @Test
    @DisplayName("백색 승리 상태에서 board 명령시 board 반환")
    void testBoard() {
        assertThat(this.whiteWin.board()).isInstanceOf(Board.class);
    }

    @Test
    @DisplayName("백색 승리 상태에서 winner 명령시 백색 반환")
    void testWinner() {
        assertThat(this.whiteWin.winner()).isEqualTo("백색");
    }

    @Test
    @DisplayName("백색 승리 상태에서 end 명령시 End 상태 반환")
    void testEnd() {
        assertThat(this.whiteWin.end()).isInstanceOf(End.class);
    }

    @Test
    @DisplayName("백색 승리 상태에서 isInit 명령시 false 반환")
    void testIsInitTure() {
        assertThat(this.whiteWin.isInit()).isFalse();
    }

    @Test
    @DisplayName("백색 승리 상태에서 isRunning 명령시 false 반환")
    void testIsRunningFalse() {
        assertThat(this.whiteWin.isRunning()).isFalse();
    }

    @Test
    @DisplayName("백색 승리 상태에서 isFinished 명령시 true 반환")
    void testIsFinishedTrue() {
        assertThat(this.whiteWin.isFinished()).isTrue();
    }

    @Test
    @DisplayName("백색 승리 상태에서 isNotEnd 명령시 true 반환")
    void testIsNotEndTrue() {
        assertThat(this.whiteWin.isNotEnd()).isTrue();
    }

    @Test
    @DisplayName("백색 승리 상태에서 movablePath 명령시 예외 반환")
    void testMovablePathException() {
        assertThatThrownBy(() -> this.whiteWin.movablePath(Position.of("a2")))
            .isInstanceOf(IllegalStateException.class);
    }

    @Test
    @DisplayName("백색 승리 상태에서 state 명령시 백색 승리 반환")
    void testState() {
        assertThat(this.whiteWin.state()).isInstanceOf(WhiteWin.class);
    }
}