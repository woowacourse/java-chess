package domain;

import chess.domain.Row;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class RowTest {
    private static final int MIN_ROW_ELEMENT = 1;
    private static final int MAX_ROW_ELEMENT = 8;

    @Test
    void 행이_잘_생성되었는지_확인() {
        List<Row> rows = new ArrayList<>();

        for (int i = MIN_ROW_ELEMENT; i <= MAX_ROW_ELEMENT; i++) {
            rows.add(Row.valueOf(String.valueOf(i)));
        }

        assertTrue(rows.containsAll(Row.getRows()));
    }
}
