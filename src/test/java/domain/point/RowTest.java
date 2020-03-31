package domain.point;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class RowTest {

    @Test
    @DisplayName("find 테스트 input 이 String 일때")
    void findColumnType_String_Input() {
        assertThat(Row.findRowType("8")).isEqualTo(Row.EIGHT);
        assertThat(Row.findRowType("7")).isEqualTo(Row.SEVEN);
        assertThat(Row.findRowType("6")).isEqualTo(Row.SIX);
        assertThat(Row.findRowType("5")).isEqualTo(Row.FIVE);
        assertThat(Row.findRowType("4")).isEqualTo(Row.FOUR);
        assertThat(Row.findRowType("3")).isEqualTo(Row.THREE);
        assertThat(Row.findRowType("2")).isEqualTo(Row.TWO);
        assertThat(Row.findRowType("1")).isEqualTo(Row.ONE);
    }

    @Test
    @DisplayName("find 테스트 input 이 Integer 일때")
    void findColumnType_Integer_Input() {
        assertThat(Row.findRowType(8)).isEqualTo(Row.EIGHT);
        assertThat(Row.findRowType(7)).isEqualTo(Row.SEVEN);
        assertThat(Row.findRowType(6)).isEqualTo(Row.SIX);
        assertThat(Row.findRowType(5)).isEqualTo(Row.FIVE);
        assertThat(Row.findRowType(4)).isEqualTo(Row.FOUR);
        assertThat(Row.findRowType(3)).isEqualTo(Row.THREE);
        assertThat(Row.findRowType(2)).isEqualTo(Row.TWO);
        assertThat(Row.findRowType(1)).isEqualTo(Row.ONE);
    }
}
