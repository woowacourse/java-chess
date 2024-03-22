package view;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CommandTest {
    @Test
    @DisplayName("사용자의 입력에 맞는 Command를 반환한다.")
    void of() {
        final String start = "start";
        final String end = "end";
        final String move = "move";

        Command commandStart = Command.of(start);
        Command commandEnd = Command.of(end);
        Command commandMove = Command.of(move);

        assertAll(
                () -> assertThat(commandStart).isEqualTo(Command.START),
                () -> assertThat(commandEnd).isEqualTo(Command.END),
                () -> assertThat(commandMove).isEqualTo(Command.MOVE)
        );
    }
}
