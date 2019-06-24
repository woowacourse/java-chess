package chess.model;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SquareTest {
    @Test
    void of_생성_테스트() {
        for (Column column : Column.values()) {
            for (Row row : Row.values()) {
                assertThat(Square.of(column, row).toString()).isEqualTo(column.toString() + row.toString());
            }
        }
    }
}
