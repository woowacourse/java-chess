package chess.domain.command;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThatCode;

class CommandTokensTest {

    @DisplayName("CommandToken이 받는 문자열 리스트의 크기는 1 혹은 3만이 유효하다.")
    @ParameterizedTest
    @ValueSource(ints = {0, 2, 4})
    void cannotMakeCommandTokens(int tokenSize) {
        List<String> commandTokens = new ArrayList<>();
        for (int i = 0; i < tokenSize; i++) {
            commandTokens.add("move");
        }

        assertThatCode(() -> CommandTokens.from(commandTokens))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("유효하지 않은 명령어 토큰입니다.");
    }
}
