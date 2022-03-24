package chess.view;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

public class InputOptionTest {

    @ParameterizedTest
    @CsvSource(value = {"start:START", "end:END", "move a1 a2:MOVE"}, delimiter = ':')
    @DisplayName("입력 형식에 바른 값을 입력받았을 때, InputOption의 Enum 값을 반환한다")
    void correctInputCheck(String input, InputOption result) {
        assertThat(InputOption.from(input)).isEqualTo(result);
    }

    @ParameterizedTest
    @ValueSource(strings = {"test", "startt", "endd", "move a b", "move a1 b", "move", "move a9 a1",
        "amove a1 a2"})
    @DisplayName("입력 형식이 아닌 값을 입력받았을 때, exception을 일으킨다")
    void incorrectInputCheck(String input) {
        assertThatThrownBy(() -> InputOption.from(input))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("[ERROR] 지원하지 않는 옵션입니다");
    }
}
