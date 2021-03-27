package domain;

import domain.exception.InvalidPositionException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class RowTest {
    @DisplayName("5를 3로 변환 가능하다.")
    @ParameterizedTest
    @CsvSource(value = {"3:5", "5:3"}, delimiter = ':')
    void index_machine_test(char input, int y) {
        assertThat(Row.convertRow(input).getIndex()).isEqualTo(y);
    }

    @DisplayName("잘못된 위치가 들어오는 경우 예외처리한다.")
    @Test
    void invalid_input() {
        assertThatThrownBy(() -> Row.convertRow('x'))
                .isInstanceOf(InvalidPositionException.class);
    }
}