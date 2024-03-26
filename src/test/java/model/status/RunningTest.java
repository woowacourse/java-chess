package model.status;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import exception.InvalidStatusException;
import java.util.List;
import model.ChessGame;
import model.command.CommandLine;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RunningTest {

    @DisplayName("러닝 상태일때 상태를 확인한다.")
    @Test
    void checkRunning() {
        final CommandLine startCommand = CommandLine.from(List.of("start"));
        final GameStatus gameStatus = StatusFactory.create(startCommand);

        assertThat(gameStatus.isRunning()).isTrue();
    }

    @DisplayName("러닝 상태일 때 play 후 상태를 확인한다.")
    @Test
    void checkRunningAfterPlay() {
        final CommandLine startCommand = CommandLine.from(List.of("start"));
        final GameStatus gameStatus = StatusFactory.create(startCommand);
        final ChessGame chessGame = ChessGame.setupStartingPosition();

        final CommandLine moveCommand = CommandLine.from(List.of("move", "a2", "a3"));
        assertThat(gameStatus.play(moveCommand, chessGame))
                .isInstanceOf(Running.class);
    }

    @DisplayName("러닝 상태에서 end 후 상태를 학인한다.")
    @Test
    void checkRunningAfterEnd() {
        final CommandLine startCommand = CommandLine.from(List.of("start"));
        final GameStatus gameStatus = StatusFactory.create(startCommand);
        final ChessGame chessGame = ChessGame.setupStartingPosition();

        final CommandLine endCommand = CommandLine.from(List.of("end"));
        assertThat(gameStatus.play(endCommand, chessGame))
                .isInstanceOf(End.class);
    }

    @DisplayName("러닝 상태에서 start 하면 예외가 발생한다.")
    @Test
    void failToStartIfAlreadyRunning() {
        //given
        final CommandLine startCommand = CommandLine.from(List.of("start"));
        final GameStatus gameStatus = StatusFactory.create(startCommand);
        final ChessGame chessGame = ChessGame.setupStartingPosition();

        //when && then
        assertThatThrownBy(() -> gameStatus.play(startCommand, chessGame))
                .isInstanceOf(InvalidStatusException.class);
    }
}
