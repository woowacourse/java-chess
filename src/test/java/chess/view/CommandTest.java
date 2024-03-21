package chess.view;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

class CommandTest {

    @ParameterizedTest
    @ValueSource(strings = {"hi", "1", "aru"})
    @NullAndEmptySource
    @DisplayName("올바르지 않은 명령어가 주어지면 예외를 발생한다.")
    void invalidCommandTest(String command) {
        assertThatThrownBy(() -> Command.from(command))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("존재하지 않는 명령어입니다.");
    }
}
