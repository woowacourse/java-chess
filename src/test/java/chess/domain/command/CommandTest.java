package chess.domain.command;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class CommandTest {
    @DisplayName("start 명령어 생성을 확인한다.")
    @Test
    void start_명령어_생성() {
        Command command = new Command("start");

        boolean isStart = command.isStart();

        assertThat(isStart).isTrue();
    }

    @DisplayName("end 명령어 생성을 확인한다.")
    @Test
    void end_명령어_생성() {
        Command command = new Command("end");

        boolean isEnd = command.isEnd();

        assertThat(isEnd).isTrue();
    }

    @DisplayName("status 명령어 생성을 확인한다.")
    @Test
    void status_명령어_생성() {
        Command command = new Command("status");

        boolean isStatus = command.isStatus();

        assertThat(isStatus).isTrue();
    }

    @DisplayName("move 명령어 생성을 확인한다.")
    @Test
    void move_명령어_생성() {
        Command command = new Command("move a3 a4");

        boolean isMove = command.isMove();

        assertThat(isMove).isTrue();
    }

    @DisplayName("첫번째 명령어를 검증한다.")
    @Test
    void 첫번째_명령어_검증() {
        Command command = new Command("move a3 a4");

        assertThatThrownBy(command::validateRightFirstCommand)
        .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("진행 명령어를 검증한다.")
    @Test
    void 진행_명령어_검증() {
        Command command = new Command("start");

        assertThatThrownBy(command::validateRunningCommand)
                .isInstanceOf(IllegalArgumentException.class);
    }
}
