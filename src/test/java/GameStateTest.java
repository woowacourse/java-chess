import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import chess.gamestate.Finished;
import chess.gamestate.GameState;
import chess.gamestate.Ready;
import chess.gamestate.Running;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class GameStateTest {

    @Test
    @DisplayName("레디 상태에서 start 입력이 들어오는 경우 상태 변화 테스트")
    void createReadyState() {
        GameState ready = new Ready();
        ready = ready.operateCommand("start");
        assertThat(ready instanceof Running).isTrue();
    }

    @Test
    @DisplayName("레디 상태에서 start 외의 입력이 들어오는 경우 예외처리 테스트")
    void invalidInputWhenRunningState() {
        assertThatIllegalArgumentException()
            .isThrownBy(() -> new Ready().operateCommand("end"))
            .withMessage("start 이외의 명령은 입력할 수 없습니다.");

        assertThatIllegalArgumentException()
            .isThrownBy(() -> new Ready().operateCommand("status"))
            .withMessage("start 이외의 명령은 입력할 수 없습니다.");

        assertThatIllegalArgumentException()
            .isThrownBy(() -> new Ready().operateCommand("move b2 b3"))
            .withMessage("start 이외의 명령은 입력할 수 없습니다.");
    }

    @Test
    @DisplayName("레디 상태에서 start 입력이 들어오는 경우 상태 변화 테스트")
    void createRunningState() {
        GameState running = new Running();

        assertThat(running.operateCommand("move b2 b3") instanceof Running).isTrue();
        assertThat(running.operateCommand("end") instanceof Finished).isTrue();
        assertThat(running.operateCommand("status") instanceof Running).isTrue();
    }

    @Test
    @DisplayName("레디 상태에서 start 외의 입력이 들어오는 경우 예외처리 테스트")
    void invalidInputWhenReadyState() {
        assertThatIllegalArgumentException()
            .isThrownBy(() -> new Running().operateCommand("start"))
            .withMessage("올바르지 않은 입력입니다.");
    }

    @Test
    @DisplayName("게임이 끝난 상태에서 입력이 들어오는 경우 예외처리 테스트")
    void operateCommandWhenFinishedState() {
        assertThatIllegalArgumentException()
            .isThrownBy(() -> new Finished().operateCommand("start"))
            .withMessage("올바르지 않은 입력입니다.");
    }
}
