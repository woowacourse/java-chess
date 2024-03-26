package model.status;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import exception.InvalidStatusException;
import java.util.List;
import model.ChessGame;
import model.command.CommandLine;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class EndTest {

    @DisplayName("종료 상태에서 play하면 예외가 발생한다.")
    @Test
    void failToPlayIfStatusEnd() {
        //given
        final ChessGame chessGame = ChessGame.setupStartingPosition();
        final CommandLine startCommand = CommandLine.from(List.of("start"));
        final GameStatus gameStatus = StatusFactory.create(startCommand);
        final CommandLine endCommand = CommandLine.from(List.of("end"));

        //when
        final GameStatus play = gameStatus.play(endCommand, chessGame);

        //then
        assertThatThrownBy(() -> play.play(endCommand, chessGame))
                .isInstanceOf(InvalidStatusException.class);
    }

    @DisplayName("종료 상태일 때 상태를 확인한다.")
    @Test
    void checkRunning() {
        final CommandLine endCommand = CommandLine.from(List.of("end"));
        final GameStatus gameStatus = StatusFactory.create(endCommand);

        assertThat(gameStatus.isRunning()).isFalse();
    }
}
