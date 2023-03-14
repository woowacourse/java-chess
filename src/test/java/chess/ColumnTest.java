package chess;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class ColumnTest {
    @DisplayName("세로 입력값에 대한 index 반환 테스트")
    @ParameterizedTest
    @CsvSource(value = {"8:0", "7:1", "6:2", "5:3", "4:4", "3:5", "2:6", "1:7"}, delimiter = ':')
    void findIndex(final String type, final int expectedIndex) {
        assertThat(Column.findIndex(type)).isEqualTo(expectedIndex);
    }
}
