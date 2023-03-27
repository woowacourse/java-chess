package chess.controller.status;

import chess.controller.Command;
import chess.controller.CommandType;
import chess.service.ChessGameService;
import chess.service.MockServiceManager;
import chess.service.Service;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class StatusControllerTest {
    private ChessGameService chessGameService;

    @BeforeAll
    void init() {
        final Service mockServiceManager = new MockServiceManager();
        chessGameService = mockServiceManager.chessGameService();
    }

    @Test
    @DisplayName(value = "게임이 status 상태일 때 start를 입력하면 예외가 발생한다.")
    void checkCommandStart() {
        // given
        final StatusController statusController = new StatusController(1L, chessGameService);
        final Command command = new Command(CommandType.START, List.of("start"));

        // when, then
        assertThatThrownBy(() -> statusController.checkCommand(command))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이미 시작이 완료되었습니다.");
    }

    @Test
    @DisplayName(value = "게임이 status 상태일 때 end를 입력하면 게임이 종료된다.")
    void checkCommandEnd() {
        // given
        final StatusController statusController = new StatusController(1L, chessGameService);
        final Command command = new Command(CommandType.END, List.of("end"));

        // when
        Controller controller = statusController.checkCommand(command);

        // then
        assertThat(controller)
                .isInstanceOf(EndController.class);
    }

    @Test
    @DisplayName(value = "게임이 status 상태일 때 move를 입력하면 이동하도록 제어한다.")
    void checkCommandMove() {
        // given
        final StatusController statusController = new StatusController(1L, chessGameService);
        final Command command = new Command(CommandType.MOVE, List.of("move", "a2", "a4"));

        // when
        Controller controller = statusController.checkCommand(command);

        // then
        assertThat(controller)
                .isInstanceOf(MoveController.class);
    }

    @Test
    @DisplayName(value = "게임이 status 상태일 때 실행 중인지 체크하면 true를 반환한다")
    void isRun() {
        // given
        final StatusController statusController = new StatusController(1L, chessGameService);

        // when
        boolean isRun = statusController.isRun();

        // then
        assertThat(isRun)
                .isTrue();
    }

    @Test
    @DisplayName("게임이 진행 중인 상태가 아니라면 end 상태로 변경한다.")
    void getStatus() {
        // given
        final StatusController statusController = new StatusController(1L, chessGameService);

        // when
        Controller controller = statusController.getStatus(false);

        // then
        assertThat(controller)
                .isInstanceOf(EndController.class);
    }
}
