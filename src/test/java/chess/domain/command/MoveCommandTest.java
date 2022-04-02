package chess.domain.command;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class MoveCommandTest {

    @DisplayName("move입력이 들어왔을 때 MoveCommand가 생성되는지 테스트")
    @Test
    void construct() {
        Command command = Command.from("move a2 a3");

        assertThat(command).isInstanceOf(MoveCommand.class);
    }
}
