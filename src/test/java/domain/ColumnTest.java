package domain;

import domain.exception.InvalidPositionException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ColumnTest {
    @DisplayName("a를 0로 변환 가능하다.")
    @ParameterizedTest
    @CsvSource(value = {"a:0", "c:2"}, delimiter = ':')
    void index_machine_test(char input, int y) {
        assertThat(Column.convertColumn(input).getIndex()).isEqualTo(y);
    }

    @DisplayName("잘못된 위치가 들어오는 경우 예외처리한다.")
    @Test
    void invalid_input() {
        assertThatThrownBy(() -> Column.convertColumn('2'))
                .isInstanceOf(InvalidPositionException.class);
    }
}