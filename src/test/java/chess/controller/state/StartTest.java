package chess.controller.state;

import chess.controller.Command;
import chess.domain.chess.ChessGame;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class StartTest {

    @Test
    @DisplayName(value = "게임이 시작 상태일 때 사용자가 입력한 명령어가 move면 예외가 발생한다.")
    void checkCommandFailWhenMove() {
        // given
        final Start start = new Start(new ChessGame());
        final Command command = new Command(CommandType.MOVE, List.of("move a2"));

        // when, then
        assertThatThrownBy(() -> start.checkCommand(command))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("게임이 시작되지 않았습니다.");
    }

    @Test
    @DisplayName(value = "게임이 시작 상태일 때 사용자가 입력한 명령어가 end면 게임이 종료된다.")
    void checkCommand() {
        // given
        final Start start = new Start(new ChessGame());
        final Command command = new Command(CommandType.END, List.of("end"));

        // when
        State state = start.checkCommand(command);

        // then
        assertThat(state)
                .isInstanceOf(End.class);
    }

    @Test
    @DisplayName(value = "게임이 시작 상태일 때 실행 중인지 체크하면 true를 반환한다")
    void isRun() {
        // given
        final Start start = new Start(new ChessGame());

        // when
        boolean isRun = start.isRun();

        // then
        assertThat(isRun)
                .isTrue();
    }
}
