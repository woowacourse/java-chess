package chess.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static chess.domain.Row.from;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class RowTest {
    @DisplayName("Row값은 a-f까지 값을 가집니다.")
    @ParameterizedTest
    @ValueSource(strings = {"a", "f"})
    void createSuccess(String row) {
        //then
        assertDoesNotThrow(() -> from(row));
    }

    @DisplayName("Row값에 a-f이외의 값을 입력 할 경우 예외가 발생합니다.")
    @ParameterizedTest
    @ValueSource(strings = {"i", "1"})
    void createFail(String row) {
        //then
        assertThatThrownBy(() -> from(row))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("두 Row를 비교해서 방향을 설정합니다.")
    @ParameterizedTest
    @CsvSource(value = {"a:1", "f:-1", "c:0"}, delimiter = ':')
    void rowDirectionTest(String row, int result) {
        //then
        assertThat(Row.ROW_C.direction(from(row))).isEqualTo(result);
    }

    @DisplayName("두 Row에 차이 거리차이를 계산합니다.")
    @ParameterizedTest
    @CsvSource(value = {"b:6", "d:4", "h:0"}, delimiter = ':')
    void rowDistanceTest(String row, int result) {
        //then
        assertThat(Row.ROW_H.distance(from(row))).isEqualTo(result);
    }
}
