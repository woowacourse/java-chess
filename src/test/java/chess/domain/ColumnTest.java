package chess.domain;

import chess.domain.Column;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ColumnTest {
    @Test
    void 행이_잘_생성되었는지_확인() {
        List<String> columnElements = Arrays.asList("a", "b", "c", "d", "e", "f", "g", "h");
        List<Column> columns = new ArrayList<>();

        for (String columnElement : columnElements) {
            columns.add(Column.valueOf(String.valueOf(columnElement)));
        }

        assertTrue(columns.containsAll(Column.getColumns()));
    }

    @Test
    void 다음_열_반환_확인() {
        assertThat(Column.valueOf("a").moveColumn(2)).isEqualTo("c");
    }

    @Test
    void 이전_열_반환_확인() {
        assertThat(Column.valueOf("d").moveColumn(-1)).isEqualTo("c");
    }

    @Test
    void 열_차이() {
        Column column = Column.valueOf("d");
        assertThat(column.getDifference(Column.valueOf("a"))).isEqualTo(3);
    }
}
