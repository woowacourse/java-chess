package domain.command;

import static org.junit.jupiter.api.Assertions.*;

import java.util.NoSuchElementException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class CommandsTest {

    @ParameterizedTest
    @ValueSource(strings = {"star", "123", "move a b", "en d"})
    @DisplayName("찾으려는 커맨드가 유효하지 않을 때 예외가 발생한다")
    void invalidCommand(final String input) {
        Assertions.assertThatThrownBy(() -> Commands.from(input))
                .isInstanceOf(NoSuchElementException.class)
                .hasMessage(String.format("찾으려는 커맨드: %s,가 존재하지 않습니다.", input));
    }

}
