package chess.model.game;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.model.board.Board;
import chess.model.board.InitialBoardFactory;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GameStatusTest {

    GameStatus gameStatus;
    Board board = new InitialBoardFactory().generate();

    @BeforeEach
    void setUp() {
        gameStatus = new GameStatus(
            (board) -> System.out.println("executeStart"),
            (commands, board) -> System.out.println("executeMove")
        );
    }

    @DisplayName("ready에서 start를 할 수 있다")
    @Test
    void readyToStart() {
        GameStatus currentStatus = gameStatus.action(List.of("start"), board);

        assertThat(gameStatus.isReady()).isTrue();
        assertThat(currentStatus.isStarted()).isTrue();
    }

    @DisplayName("start에서 start를 하면 예외가 발생한다")
    @Test
    void startToStart() {
        gameStatus = gameStatus.action(List.of("start"), board);

        assertThat(gameStatus.isStarted()).isTrue();
        assertThatThrownBy(() -> gameStatus.action(List.of("start"), board))
            .isInstanceOf(UnsupportedOperationException.class)
            .hasMessage("게임이 이미 진행 중 입니다.");
    }

    @DisplayName("start에서 move를 할 수 있다")
    @Test
    void startToMove() {
        gameStatus = gameStatus.action(List.of("start"), board);
        GameStatus currentStatus = gameStatus.action(List.of("move", "a2", "a4"), board);

        assertThat(gameStatus.isStarted()).isTrue();
        assertThat(currentStatus.isMoved()).isTrue();
    }

    @DisplayName("move에서 move를 할 수 있다")
    @Test
    void moveToMove() {
        gameStatus = gameStatus.action(List.of("start"), board);
        gameStatus = gameStatus.action(List.of("move", "a2", "a4"), board);
        GameStatus currentStatus = gameStatus.action(List.of("move", "a7", "a5"), board);

        assertThat(gameStatus.isMoved()).isTrue();
        assertThat(currentStatus.isMoved()).isTrue();
    }

    @DisplayName("ready에서 move를 하면 예외가 발생한다")
    @Test
    void readyToMove() {
        assertThat(gameStatus.isReady()).isTrue();
        assertThatThrownBy(() -> gameStatus = gameStatus.action(List.of("move", "a2", "a4"), board))
            .isInstanceOf(UnsupportedOperationException.class)
            .hasMessage("게임을 start 해 주세요.");
    }

    @DisplayName("ready에서 finish를 할 수 있다")
    @Test
    void readyToFinish() {
        GameStatus currentStatus = gameStatus.action(List.of("end"), board);

        assertThat(gameStatus.isReady()).isTrue();
        assertThat(currentStatus.isRunning()).isFalse();
    }

    @DisplayName("start에서 finish를 할 수 있다")
    @Test
    void startToFinish() {
        gameStatus = gameStatus.action(List.of("start"), board);
        GameStatus currentStatus = gameStatus.action(List.of("end"), board);

        assertThat(gameStatus.isStarted()).isTrue();
        assertThat(currentStatus.isRunning()).isFalse();
    }

    @DisplayName("move에서 finish를 할 수 있다")
    @Test
    void moveToFinish() {
        gameStatus = gameStatus.action(List.of("start"), board);
        gameStatus = gameStatus.action(List.of("move", "a2", "a4"), board);
        GameStatus currentStatus = gameStatus.action(List.of("end"), board);

        assertThat(gameStatus.isMoved()).isTrue();
        assertThat(currentStatus.isRunning()).isFalse();
    }
}
