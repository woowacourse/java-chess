package model.status;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import exception.InvalidStatusException;
import java.util.List;
import model.ChessBoard;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RunningTest {

    @DisplayName("러닝 상태일때 상태를 확인한다.")
    @Test
    void checkRunning() {
        final GameStatus gameStatus = Initialization.gameSetting(List.of("start"));

        assertThat(gameStatus.isRunning()).isTrue();
    }

    @DisplayName("러닝 상태일 때 play 후 상태를 확인한다.")
    @Test
    void checkRunningAfterPlay() {
        final GameStatus gameStatus = Initialization.gameSetting(List.of("start"));
        final ChessBoard chessBoard = ChessBoard.setupStartingPosition();

        final List<String> moveCommand = List.of("move", "a2", "a3");
        assertThat(gameStatus.play(moveCommand, chessBoard))
                .isInstanceOf(Running.class);
    }

    @DisplayName("러닝 상태에서 end 후 상태를 학인한다.")
    @Test
    void checkRunningAfterEnd() {
        final GameStatus gameStatus = Initialization.gameSetting(List.of("start"));
        final ChessBoard chessBoard = ChessBoard.setupStartingPosition();

        final List<String> endCommand = List.of("end");
        assertThat(gameStatus.play(endCommand, chessBoard))
                .isInstanceOf(End.class);
    }

    @DisplayName("러닝 상태에서 start 하면 예외가 발생한다.")
    @Test
    void failToStartIfAlreadyRunning() {
        //given
        final GameStatus gameStatus = Initialization.gameSetting(List.of("start"));
        final ChessBoard chessBoard = ChessBoard.setupStartingPosition();

        //when && then
        final List<String> startCommand = List.of("start");
        assertThatThrownBy(() -> gameStatus.play(startCommand, chessBoard))
                .isInstanceOf(InvalidStatusException.class);
    }
}
