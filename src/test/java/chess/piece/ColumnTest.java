package chess.piece;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class ColumnTest {

    @ParameterizedTest(name = "column: {0}")
    @ValueSource(strings = {"I", "J", "K"})
    @DisplayName("존재하지 않는 열을 요청할 경우 예외를 발생한다.")
    void findColumnException(final String value) {
        assertThatThrownBy(() -> Column.of(value))
                .hasMessageContaining("존재하지 않는 열입니다.")
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest(name = "column: {0}")
    @ValueSource(strings = {"a", "A", "h", "H"})
    @DisplayName("대소문자와 상관없이 열을 반환한다.")
    void findColumn(final String value) {
        assertThat(Column.of(value).name()).isEqualTo(value.toUpperCase());
    }
}
