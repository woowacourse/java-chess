package chess.domain;

import chess.domain.position.Column;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ColumnTest {

    @Test
    @DisplayName("세로는 1~8 사이의 숫자로 생성된다.")
    void Column_create_with_valid_range_number() {
        assertThatCode(() -> Column.from("1"))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("1~8이 아닌 숫자로 만들어진 세로는 존재하지 않는다.")
    void Column_validate_with_range_number() {
        assertThatThrownBy(() -> Column.from("10"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("1~8까지 가능합니다.");
    }
}
