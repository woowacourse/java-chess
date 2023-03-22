package chess.controller.status;

import chess.controller.Command;
import chess.controller.CommandType;
import chess.domain.chess.CampType;
import chess.domain.chess.ChessGame;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class MoveControllerTest {

    @Test
    @DisplayName(value = "게임이 움직임 상태일 때 start를 입력하면 예외가 발생한다.")
    void checkCommandStart() {
        // given
        final MoveController moveController = new MoveController(new ChessGame(), CampType.WHITE);
        final Command command = new Command(CommandType.START, List.of("start"));

        // when, then
        assertThatThrownBy(() -> moveController.checkCommand(command, () -> {
        }))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이미 시작이 완료되었습니다.");
    }

    @Test
    @DisplayName(value = "게임이 움직임 상태일 때 end를 입력하면 게임이 종료된다.")
    void checkCommandEnd() {
        // given
        final MoveController moveController = new MoveController(new ChessGame(), CampType.WHITE);
        final Command command = new Command(CommandType.END, List.of("end"));

        // when
        Controller controller = moveController.checkCommand(command, () -> {
        });

        // then
        assertThat(controller)
                .isInstanceOf(EndController.class);
    }

    @ParameterizedTest(name = "게임이 움직임 상태일 때 올바르지 않은 명령어 형식을 입력하면 예외가 발생한다.")
    @ValueSource(strings = {"move", "move a2", "", " move ", "move a2 a3 a5", "move a2a3"})
    void checkCommandValidate(final String commands) {
        // given
        final MoveController moveController = new MoveController(new ChessGame(), CampType.WHITE);
        final Command command = new Command(CommandType.MOVE, Arrays.asList(commands.split(" ")));

        // when, then
        assertThatThrownBy(() -> moveController.checkCommand(command, () -> {
        }))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("'move source위치 target위치 - 예. move b2 b3'와 같은 형태로 입력해 주세요.");
    }

    @Test
    @DisplayName(value = "게임이 움직임 상태일 때 실행 중인지 체크하면 true 반환한다")
    void isRun() {
        // given
        final MoveController moveController = new MoveController(new ChessGame(), CampType.WHITE);

        // when
        boolean isRun = moveController.isRun();

        // then
        assertThat(isRun)
                .isTrue();
    }
}
