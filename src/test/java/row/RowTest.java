package row;

import column.Column;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class RowTest {

    @ParameterizedTest
    @ValueSource(ints = {-1, 8})
    @DisplayName("움직일 수 있는 범위를 벗어날 수 없다.")
    void overRangePosition(int position) {
        Column column = new Column(0);

        Assertions.assertThatThrownBy(() -> column.move(position))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("움직일 수 있는 위치가 아닙니다.");
    }
}
