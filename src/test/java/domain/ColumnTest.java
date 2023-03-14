package domain;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class ColumnTest {

    @Test
    void 인덱스_오름차순으로_정렬된_Column_목록을_반환한다() {
        //given, when
        List<Column> expected = List.of(Column.A, Column.B, Column.C, Column.D, Column.E, Column.F, Column.G, Column.H);
        List<Column> actual = Column.getOrderedColumns();

        //then
        assertThat(expected).isEqualTo(actual);
    }
}
