package chess.view;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class CommandTest {

    @ParameterizedTest
    @ValueSource(strings = {"s", "e", "START"})
    @DisplayName("존재하지 않은 커맨드 입력 시 예외발생")
    void notFoundCommand(String command) {
        assertThatThrownBy(() -> Command.from(command))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("존재하지 않는 커맨드입니다.");
    }
}
