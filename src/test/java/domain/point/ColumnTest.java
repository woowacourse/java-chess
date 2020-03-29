package domain.point;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ColumnTest {

    @Test
    @DisplayName("find 테스트 input 이 String 일때")
    void findColumnType_String_Input() {
        assertThat(Column.findColumnType("a")).isEqualTo(Column.A);
        assertThat(Column.findColumnType("b")).isEqualTo(Column.B);
        assertThat(Column.findColumnType("c")).isEqualTo(Column.C);
        assertThat(Column.findColumnType("d")).isEqualTo(Column.D);
        assertThat(Column.findColumnType("e")).isEqualTo(Column.E);
        assertThat(Column.findColumnType("f")).isEqualTo(Column.F);
        assertThat(Column.findColumnType("g")).isEqualTo(Column.G);
        assertThat(Column.findColumnType("h")).isEqualTo(Column.H);
    }

    @Test
    @DisplayName("find 테스트 input 이 Integer 일때")
    void findColumnType_Integer_Input() {
        assertThat(Column.findColumnType(1)).isEqualTo(Column.A);
        assertThat(Column.findColumnType(2)).isEqualTo(Column.B);
        assertThat(Column.findColumnType(3)).isEqualTo(Column.C);
        assertThat(Column.findColumnType(4)).isEqualTo(Column.D);
        assertThat(Column.findColumnType(5)).isEqualTo(Column.E);
        assertThat(Column.findColumnType(6)).isEqualTo(Column.F);
        assertThat(Column.findColumnType(7)).isEqualTo(Column.G);
        assertThat(Column.findColumnType(8)).isEqualTo(Column.H);
    }
}
