package chess.domain;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static chess.domain.Row.from;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class ColumnTest {
    @ParameterizedTest
    @ValueSource(strings = {"1", "8"})
    void createSuccess(String column) {
        //then
        assertDoesNotThrow(() -> Column.from(column));
    }

    @ParameterizedTest
    @ValueSource(strings = {"i", "9"})
    void createFail(String row) {
        //then
        assertThatThrownBy(() -> from(row))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @CsvSource(value = {"1:-1", "2:0", "7:1"}, delimiter = ':')
    void rowDirectionTest(String column, int result) {
        //then
        assertThat(Column.COLUMN_2.direction(Column.from(column))).isEqualTo(result);
    }

    @ParameterizedTest
    @CsvSource(value = {"3:2", "1:0", "7:6"}, delimiter = ':')
    void rowDistanceTest(String column, int result) {
        //then
        assertThat(Column.COLUMN_1.distance(Column.from(column))).isEqualTo(result);
    }
}
