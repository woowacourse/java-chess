package chess.domain;

import chess.domain.position.Column;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ColumnTest {

    @Test
    @DisplayName("세로는 1~8 사이의 숫자로 생성된다.")
    void Column_create_with_valid_range_number() {
        var result = Column.valueOf("8");
        assertThat(result).isInstanceOf(Column.class);
    }

    @Test
    @DisplayName("1~8이 아닌 숫자로 만들어진 세로는 존재하지 않는다.")
    void Column_validate_with_range_number() {
        assertThatThrownBy(() -> {
            Column.valueOf("10");
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("값을 넘겨주어 column을 최신화한다.")
    void Column_Update_with_value() {
        Column column = Column.valueOf("2");

        assertThat(column.update(1)).isEqualTo(Column.valueOf("3"));
    }

    @Test
    @DisplayName("column과 column의 차이를 구한다.")
    void Column_Subtract_with_other_column() {
        Column column = Column.valueOf("2");
        var sut = Column.valueOf("4");

        var result = sut.subtractColumn(column);

        assertThat(result).isEqualTo(2);
    }

    @ParameterizedTest
    @CsvSource(value = {"2, 4, 1", "2, 2, 0", "2, 1, -1"})
    @DisplayName("column과 column의 크기를 비교하여 방향을 찾는다..")
    void Column_Find_direction_with_other_column(String value1, String value2, int compareResult) {
        var sut = Column.valueOf(value1);
        Column column = Column.valueOf(value2);

        var result = sut.findDirection(column);

        assertThat(result).isEqualTo(compareResult);
    }
}
