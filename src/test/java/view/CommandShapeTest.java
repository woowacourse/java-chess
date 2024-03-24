package view;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CommandShapeTest {
    @Test
    @DisplayName("사용자의 입력에 맞는 Command를 반환한다.")
    void of() {
        final String start = "start";
        final String end = "end";
        final String move = "move";

        CommandShape commandStart = CommandShape.of(start);
        CommandShape commandEnd = CommandShape.of(end);
        CommandShape commandMove = CommandShape.of(move);

        assertAll(
                () -> assertThat(commandStart).isEqualTo(CommandShape.START),
                () -> assertThat(commandEnd).isEqualTo(CommandShape.END),
                () -> assertThat(commandMove).isEqualTo(CommandShape.MOVE)
        );
    }
}
