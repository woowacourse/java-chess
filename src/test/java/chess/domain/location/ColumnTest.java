package chess.domain.location;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class ColumnTest {
    @DisplayName("A~H 사이의 알파벳을 이용해 객체를 생성할 수 있다.")
    @ParameterizedTest
    @ValueSource(strings = {"a", "A", "h", "H"})
    void constructTest(String input) {
        assertThatCode(() -> Column.of(input))
                .doesNotThrowAnyException();
    }

    @DisplayName("범위 이외의 알파벳을 이용해 객체를 생성하면 예외가 발생한다.")
    @Test
    void outOfBoundConstructTest() {
        assertThatThrownBy(() -> Column.of("I"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("잘못된 Column 입력입니다.");
    }
}
