package chess.domain.state;

import chess.domain.MoveParameter;
import chess.domain.Turn;
import chess.domain.board.Board;
import chess.domain.board.EnumRepositoryBoardInitializer;
import chess.domain.player.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class RunningStateTest {
    RunningState runningState;

    @BeforeEach
    void setUp() {
        runningState = new RunningState(Board.of(new EnumRepositoryBoardInitializer()));
    }

    @Test
    @DisplayName("RunningState는 start 메서드를 지원하지 않음")
    void start() {
        assertThatThrownBy(() -> runningState.start())
                .isInstanceOf(UnsupportedOperationException.class);
    }

    @Test
    @DisplayName("RunningState는 move 시 board의 piece를 move하고 RunningState를 반환함")
    void move() {
        assertThat(runningState.move(MoveParameter.of(Arrays.asList("B1", "A3")), Turn.from(Player.WHITE)))
                .isInstanceOf(RunningState.class);
    }

    @Test
    @DisplayName("RunningState는 end 시 게임을 종료하고 EndState를 반환함")
    void end() {
        assertThat(runningState.end())
                .isInstanceOf(EndState.class);
    }

    @Test
    @DisplayName("RunningState는 getBoard 시 현재 상태의 board를 반환함")
    void getBoard() {
        assertThat(runningState.getBoard())
                .isInstanceOf(Board.class);
    }

}