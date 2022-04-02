package chess.domain.command;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class StatusCommandTest {

    @DisplayName("status 입력이 들어왔을 때 StatusCommand가 생성되는지 테스트")
    @Test
    void construct() {
        Command command = Command.from("status");

        assertThat(command).isInstanceOf(StatusCommand.class);
    }
}
