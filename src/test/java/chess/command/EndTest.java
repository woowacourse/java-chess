package chess.command;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class EndTest {

    @Test
    @DisplayName("end가 들어와서 게임이 끝났을때는 다시 end가 들어와도 그 상태 그대로이다.")
    void turnState() {
        String input = "end";
        Command endCommand = new End(input);
        assertThat(endCommand.turnState(input)).isExactlyInstanceOf(End.class);
    }

    @Test
    @DisplayName("게임이 끝났을 때, status가 들어오면 Status상태로 변경되고 그렇지 않으면 End 상태이다.")
    void turnFinalState() {
        String input = "end";
        Command endCommand = new End(input);
        String status = "status";
        assertThat(endCommand.turnFinalState(status)).isExactlyInstanceOf(Status.class);
    }

    @Test
    @DisplayName("command가 end이면 true를 반환한다.")
    void isEnd() {
        Command command = new End("end");
        assertThat(command.isEnd()).isTrue();
    }
}
