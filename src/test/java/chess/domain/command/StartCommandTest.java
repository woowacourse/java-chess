package chess.domain.command;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class StartCommandTest {

    @DisplayName("start 입력이 들어왔을 때 StartCommand가 생성되는지 테스트")
    @Test
    void construct() {
        Command command = Command.from("start");

        assertThat(command).isInstanceOf(StartCommand.class);
    }
}
