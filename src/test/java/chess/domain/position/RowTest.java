package chess.domain.position;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class RowTest {
    @Test
    @DisplayName("숫자를 입력 시, 해당하는 Row을 조회한다.")
    void find() {
        // given
        Row row = Row.FIVE;
        Row findRow = Row.find(5);

        // when
        boolean result = row.equals(findRow);

        // then
        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("Row을 입력 시, 두 값의 차이를 반환한다.")
    void getDifference() {
        // given
        Row Row1 = Row.FOUR;
        Row Row2 = Row.THREE;

        // when
        int result = Row1.getDifference(Row2);

        // then
        assertThat(result).isEqualTo(1);
    }

    @Test
    @DisplayName("Row의 symbol 값을 반환한다.")
    void getSymbol() {
        // given
        Row row = Row.FIVE;

        // when
        String symbol = row.getSymbol();

        // then
        assertThat(symbol).isEqualTo("5");
    }

    @Test
    @DisplayName("숫자를 입력 시, Row의 value와 더한 값의 Row을 반환한다.")
    void plusRow() {
        // given
        Row row = Row.FOUR;

        // when
        Row result = row.plusRow(1);
        Row expected = Row.FIVE;

        // then
        assertThat(result).isEqualTo(expected);
    }
}