package chess.piece;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class RowTest {

    @ParameterizedTest(name = "row: {0}")
    @ValueSource(ints = {0, 9})
    @DisplayName("존재하지 않는 행을 요청할 경우 예외를 발생한다.")
    void findColumnException(int value) {
        assertThatThrownBy(() -> Row.find(value))
                .hasMessageContaining("존재하지 않는 행입니다.")
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest(name = "row: {0}")
    @ValueSource(ints = {1, 2, 3, 4, 5, 6, 7, 8})
    @DisplayName("대소문자와 상관없이 행을 반환한다.")
    void findColumn(int value) {
        assertThatCode(() -> Row.find(value))
                .doesNotThrowAnyException();
    }
}
