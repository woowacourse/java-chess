import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ColumnTest {

    @Test
    @DisplayName("세로는 1~8 사이의 숫자로 생성된다.")
    void makeColumnTest() {
        assertThatCode(() -> {
            Column.valueOf("1");
        }).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("1~8이 아닌 숫자로 만들어진 세로는 존재하지 않는다.")
    void testValueOf() {
        assertThatThrownBy(() -> Column.valueOf("10")).isInstanceOf(IllegalArgumentException.class);
    }
}
