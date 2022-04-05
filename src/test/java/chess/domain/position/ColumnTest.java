package chess.domain.position;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ColumnTest {
    @Test
    @DisplayName("숫자를 입력 시, 해당하는 컬럼을 조회한다.")
    void find() {
        // given
        Column column = Column.C;
        Column findColumn = Column.find(3);

        // when
        boolean result = column.equals(findColumn);

        // then
        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("컬럼을 입력 시, 두 값의 차이를 반환한다.")
    void getDifference() {
        // given
        Column column1 = Column.D;
        Column column2 = Column.C;

        // when
        int result = column1.getDifference(column2);

        // then
        assertThat(result).isEqualTo(1);
    }

    @Test
    @DisplayName("컬럼의 symbol 값을 반환한다.")
    void getSymbol() {
        // given
        Column column = Column.D;

        // when
        String symbol = column.getSymbol();

        // then
        assertThat(symbol).isEqualTo("d");
    }

    @Test
    @DisplayName("숫자를 입력 시, 컬럼의 value와 더한 값의 Column을 반환한다.")
    void plusColumn() {
        // given
        Column column = Column.D;

        // when
        Column result = column.plusColumn(1);
        Column expected = Column.E;

        // then
        assertThat(result).isEqualTo(expected);
    }
}