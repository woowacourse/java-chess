package chess.manager;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CommandTest {

    @Test
    @DisplayName("문자열을 입력받아 매뉴 커맨드와 명령어 개수가 일치하면 반환해준다.")
    void of() {
        String start = "start";
        String end = "end";
        String move = "move a1 a2";
        String status = "status";
        String show = "show a2";

        Command startCommand = Command.of(start);
        Command endCommand = Command.of(end);
        Command moveCommand = Command.of(move);
        Command statusCommand = Command.of(status);
        Command showCommand = Command.of(show);

        assertThat(startCommand).isEqualTo(Command.START);
        assertThat(endCommand).isEqualTo(Command.END);
        assertThat(moveCommand).isEqualTo(Command.MOVE);
        assertThat(statusCommand).isEqualTo(Command.STATUS);
        assertThat(showCommand).isEqualTo(Command.SHOW);
    }

    @Test
    @DisplayName("메뉴가 END인지 판단한다.")
    void isEnd() {
        Command end = Command.END;
        Command start = Command.START;

        assertThat(end.isEnd()).isTrue();
        assertThat(start.isEnd()).isFalse();
    }

    @Test
    @DisplayName("메뉴가 START인지 판단한다.")
    void isStart() {
        Command start = Command.START;
        Command status = Command.STATUS;

        assertThat(start.isStart()).isTrue();
        assertThat(status.isStart()).isFalse();
    }
}