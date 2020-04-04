package chess.domain.state;

import chess.domain.MoveParameter;
import chess.domain.board.Board;
import chess.domain.board.initializer.EnumRepositoryBoardInitializer;
import chess.domain.game.ChessGame;
import chess.domain.game.Turn;
import chess.domain.player.Team;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

class RunningStateTest {
    RunningState runningState;

    @BeforeEach
    void setUp() {
        ChessGame chessGame = new ChessGame(Board.of(new EnumRepositoryBoardInitializer()), Turn.from(Team.WHITE));
        runningState = new RunningState(chessGame);
    }

    @Test
    @DisplayName("RunningState는 start 메서드에 new라는 인자를 받으면 새로운 게임을 생성")
    void start() {
        assertThat(runningState.start(Arrays.asList("new")))
                .isInstanceOf(RunningState.class);
    }

    @Test
    @DisplayName("RunningState는 move 시 board의 piece를 move하고 RunningState를 반환함")
    void move() {
        assertThat(runningState.move(MoveParameter.of(Arrays.asList("B1", "A3"))))
                .isInstanceOf(RunningState.class);
    }

    @Test
    @DisplayName("RunningState는 end 시 게임을 종료하고 EndState를 반환함")
    void end() {
        assertThat(runningState.end(Arrays.asList("save")))
                .isInstanceOf(ReadyState.class);
    }

    @Test
    @DisplayName("RunningState는 getBoard 시 현재 상태의 board를 반환함")
    void getBoard() {
        assertThat(runningState.getChessGame())
                .isInstanceOf(ChessGame.class);
    }

}