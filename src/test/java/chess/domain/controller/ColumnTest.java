package chess.domain.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import chess.controller.Column;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ColumnTest {
    @Test
    @DisplayName("문자에 해당하는 열 번호를 반환한다.")
    void findColumn() {
        String input = "a";
        assertThat(Column.findColumn(input)).isEqualTo(1);
    }

    @Test
    @DisplayName("존재하지 않는 열문자열을 입력하면 예외가 발생한다..")
    void findColumnByInvalidInput() {
        String input = "i";
        assertThatIllegalArgumentException()
                .isThrownBy(() -> Column.findColumn(input))
                .withMessage("올바르지 않은 열입니다.");
    }
}
