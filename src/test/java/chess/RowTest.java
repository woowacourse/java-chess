package chess;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class RowTest {
    @DisplayName("가로 입력값에 대한 index 반환 테스트")
    @ParameterizedTest
    @CsvSource(value = {"a:0", "b:1", "c:2", "d:3", "e:4", "f:5", "g:6", "h:7"}, delimiter = ':')
    void findIndex(final String type, final int expectedIndex) {
        assertThat(Row.findIndex(type)).isEqualTo(expectedIndex);
    }
}
