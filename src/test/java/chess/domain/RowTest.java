package chess.domain;

import chess.domain.position.Column;
import chess.domain.position.Row;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static java.util.stream.Collectors.toMap;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class RowTest {

    @Test
    @DisplayName("가로는 a~h의 알파벳으로 생성된다.")
    void makeRowTest() {
        var result = Row.valueOf("a");
        assertThat(result).isInstanceOf(Row.class);
    }

    @Test
    @DisplayName("a~h가 아닌 알파벳으로 만들어진 가로는 존재하지 않는다.")
    void testValueOf() {
        assertThatThrownBy(() -> Row.valueOf("z")).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("값을 넘겨주어 row을 최신화한다.")
    void Row_Update_with_value() {
        Row row = Row.valueOf("a");

        assertThat(row.update(1)).isEqualTo(Row.valueOf("b"));
    }

    @Test
    @DisplayName("row과 row의 차이를 구한다.")
    void row_Subtract_with_other_row() {
        Row row = Row.valueOf("a");
        var sut = Row.valueOf("c");

        var result = sut.subtractRow(row);

        assertThat(result).isEqualTo(2);
    }

    @ParameterizedTest
    @CsvSource(value = {"a, b, 1", "a, a, 0", "b, a, -1"})
    @DisplayName("column과 column의 크기를 비교하여 방향을 찾는다..")
    void Column_Find_direction_with_other_column(String value1, String value2, int compareResult) {
        var sut = Row.valueOf(value1);
        Row row = Row.valueOf(value2);

        var result = sut.findDirection(row);

        assertThat(result).isEqualTo(compareResult);
    }
}
