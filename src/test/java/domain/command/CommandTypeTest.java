package domain.command;

import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CommandTypeTest {

    @DisplayName("commnad를 찾을 수 없으면 오류를 발생한다.")
    @Test
    void parseCommand() {
        List<String> arguments = List.of("rush", "sun", "JJang");

        Assertions.assertThatThrownBy(() -> CommandType.parse(arguments))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("잘못된 move command에 대해 오류를 발생한다.")
    @Test
    void startCommand() {
        List<String> arguments = List.of("start", "rush", "sun");

        Assertions.assertThatThrownBy(() -> CommandType.parse(arguments))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("잘못된 move command에 대해 오류를 발생한다.")
    @Test
    void moveCommand() {
        List<String> arguments = List.of("move", "rush", "sun");

        Assertions.assertThatThrownBy(() -> CommandType.parse(arguments))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("잘못된 move command에 대해 오류를 발생한다.")
    @Test
    void endCommand() {
        List<String> arguments = List.of("end", "rush", "sun");

        Assertions.assertThatThrownBy(() -> CommandType.parse(arguments))
                .isInstanceOf(IllegalArgumentException.class);
    }

}
