package domain;

import chess.domain.Column;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
}
