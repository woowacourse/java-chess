package chess.model.game;

import static chess.model.game.Status.MOVE;
import static chess.model.game.Status.READY;
import static chess.model.game.Status.START;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

class GameStatusTest {

    @DisplayName("ready에서 start를 할 수 있다")
    @Test
    void readyToStart() {
        GameStatus gameStatus = new GameStatus(READY);
        assertThat(gameStatus.changeStart().isStarted()).isTrue();
    }

    @DisplayName("start에서 start를 하면 예외가 발생한다")
    @Test
    void startToStart() {
        GameStatus gameStatus = new GameStatus(START);
        assertThatThrownBy(gameStatus::changeStart)
            .isInstanceOf(UnsupportedOperationException.class)
            .hasMessage("게임이 이미 진행 중 입니다.");
    }

    @DisplayName("start에서 move를 할 수 있다")
    @Test
    void startToMove() {
        GameStatus gameStatus = new GameStatus(START);
        assertThat(gameStatus.changeMove().isMoved()).isTrue();
    }

    @DisplayName("move에서 move를 할 수 있다")
    @Test
    void moveToMove() {
        GameStatus gameStatus = new GameStatus(MOVE);
        assertThat(gameStatus.changeMove().isMoved()).isTrue();
    }

    @DisplayName("ready에서 move를 하면 예외가 발생한다")
    @Test
    void readyToMove() {
        GameStatus gameStatus = new GameStatus();
        assertThatThrownBy(gameStatus::changeMove)
            .isInstanceOf(UnsupportedOperationException.class)
            .hasMessage("게임을 start 해 주세요.");
    }

    @DisplayName("어떤 상태든 finish를 할 수 있다")
    @ParameterizedTest
    @EnumSource(value = Status.class, names = {"READY", "START", "MOVE", "END"})
    void finish(Status status) {
        GameStatus gameStatus = new GameStatus(status);
        assertThat(gameStatus.changeEnd().isEnded()).isTrue();
    }

    @DisplayName("end가 아니면 게임 진행 상태이다")
    @ParameterizedTest
    @EnumSource(value = Status.class, names = {"READY", "START", "MOVE"})
    void isRunning(Status status) {
        GameStatus gameStatus = new GameStatus(status);
        assertThat(gameStatus.isRunning()).isTrue();
    }
}
