package chess.piece;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import chess.game.Row;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class RowTest {

    @ParameterizedTest(name = "row: {0}")
    @ValueSource(ints = {0, 9})
    @DisplayName("존재하지 않는 행을 요청할 경우 예외를 발생한다.")
    void findColumnException(final int value) {
        assertThatThrownBy(() -> Row.of(value))
                .hasMessageContaining("존재하지 않는 행입니다.")
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest(name = "row: {0}")
    @ValueSource(ints = {1, 2, 3, 4, 5, 6, 7, 8})
    @DisplayName("대소문자와 상관없이 행을 반환한다.")
    void findColumn(final int value) {
        assertThatCode(() -> Row.of(value))
                .doesNotThrowAnyException();
    }
}
