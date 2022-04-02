package chess.domain.command;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class EndCommandTest {

    @DisplayName("end 입력이 들어왔을 때 EndCommand가 생성되는지 테스트")
    @Test
    void construct() {
        Command command = Command.from("end");

        assertThat(command).isInstanceOf(EndCommand.class);
    }
}
