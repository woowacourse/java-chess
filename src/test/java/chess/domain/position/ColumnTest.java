package chess.domain.position;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

public class ColumnTest {

    @ParameterizedTest
    @ValueSource(strings = {"a", "h"})
    @DisplayName("이름을 가지고 Column 을 잘 생성하는지")
    void createColumnWithName(String name) {
        Column column = Column.of(name);

        assertThat(column.getName()).isEqualTo(name);
    }

    @ParameterizedTest
    @CsvSource(value = {"0:a", "1:b", "7:h"}, delimiter = ':')
    @DisplayName("file 값을 가지고 Column 을 잘 생성하는지")
    void createColumnWithName(int file, String name) {
        Column column = Column.of(file);

        assertThat(column.getName()).isEqualTo(name);
    }

    @ParameterizedTest
    @ValueSource(strings = {"9", "k"})
    @DisplayName("존재하지 않는 이름으로 탐색할 경우 예외를 처리하는지")
    void invalidNameException(String name) {
        assertThatThrownBy(() -> Column.of(name))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("유효하지 않은 좌표입니다.");
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, 8})
    @DisplayName("존재하지 않는 file 값으로 탐색할 경우 예외를 처리하는지")
    void invalidFileException(int file) {
        assertThatThrownBy(() -> Column.of(file))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("유효하지 않은 좌표입니다.");
    }
}
