package chess.controller.status;

import chess.controller.Command;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class EndTest {

    @Test
    @DisplayName(value = "게임이 종료 상태일 때 명령어를 찾으면 예외가 발생한다.")
    void checkCommand() {
        // given
        final End end = new End();
        final Command command = new Command(CommandType.END, List.of("end"));

        // when, then
        assertThatThrownBy(() -> end.checkCommand(command))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("게임이 끝났습니다.");
    }

    @Test
    @DisplayName(value = "게임이 종료 상태일 때 실행 중인지 체크하면 false를 반환한다")
    void isRun() {
        // given
        final End end = new End();

        // when
        boolean isRun = end.isRun();

        // then
        assertThat(isRun)
                .isFalse();
    }
}
