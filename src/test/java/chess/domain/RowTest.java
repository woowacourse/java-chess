package chess.domain;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static chess.domain.Row.from;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class RowTest {
    @ParameterizedTest
    @ValueSource(strings = {"a", "f"})
    void createSuccess(String row) {
        //then
        assertDoesNotThrow(() -> from(row));
    }

    @ParameterizedTest
    @ValueSource(strings = {"i", "1"})
    void createFail(String row) {
        //then
        assertThatThrownBy(() -> from(row))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @CsvSource(value = {"a:1", "f:-1", "c:0"}, delimiter = ':')
    void rowDirectionTest(String row, int result) {
        //then
        assertThat(Row.ROW_C.direction(from(row))).isEqualTo(result);
    }

    @ParameterizedTest
    @CsvSource(value = {"b:6", "d:4", "h:0"}, delimiter = ':')
    void rowDistanceTest(String row, int result) {
        //then
        assertThat(Row.ROW_H.distance(from(row))).isEqualTo(result);
    }
}
