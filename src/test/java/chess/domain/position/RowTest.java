package chess.domain.position;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

public class RowTest {

    @ParameterizedTest
    @ValueSource(strings = {"1", "8"})
    @DisplayName("이름을 가지고 Row 을 잘 생성하는지")
    void createRowWithName(String name) {
        Row row = Row.of(name);

        assertThat(row.getName()).isEqualTo(name);
    }

    @ParameterizedTest
    @CsvSource(value = {"0:8", "1:7", "7:1"}, delimiter = ':')
    @DisplayName("file 값을 가지고 Row 을 잘 생성하는지")
    void createRowWithName(int file, String name) {
        Row row = Row.of(file);

        assertThat(row.getName()).isEqualTo(name);
    }
    
    @ParameterizedTest
    @ValueSource(ints = {-1, 8})
    @DisplayName("잘못된 rank 로 탐색할 경우 예외를 처리하는지")
    void outOfRangeException(int rank) {
        assertThatThrownBy(() -> Row.of(rank))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("유효하지 않은 좌표입니다.");
    }

    @ParameterizedTest
    @ValueSource(strings = {"9", "a"})
    @DisplayName("존재하지 않는 이름으로 탐색할 경우 예외를 처리하는지")
    void invalidNameException(String name) {
        assertThatThrownBy(() -> Row.of(name))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("유효하지 않은 좌표입니다.");
    }
}
