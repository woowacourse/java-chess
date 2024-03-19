package chess.domain.location;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class RowTest {
    @DisplayName("1~8 사이의 숫자를 이용해 객체를 생성할 수 있다.")
    @ParameterizedTest
    @ValueSource(strings = {"1", "8"})
    void constructTest(String input) {
        assertThatCode(() -> Row.of(input))
                .doesNotThrowAnyException();
    }

    @DisplayName("범위 이외의 숫자를 이용해 객체를 생성하면 예외가 발생한다.")
    @Test
    void outOfBoundConstructTest() {
        assertThatThrownBy(() -> Row.of("9"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("잘못된 Row 입력입니다.");
    }
}
