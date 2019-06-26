package chess.model;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SquareTest {
    @Test
    void of_생성_테스트() {
        for (Column column : Column.values()) {
            for (Row row : Row.values()) {
                assertThat(Square.of(column, row).toString()).isEqualTo(row.toString() + column.toString());
            }
        }
    }

    @Test
    void String_입력_팩토리메소드와_Enum_입력_팩토리메소드_비교() {
        assertThat(Square.of("a1")).isEqualTo(Square.of(Column._1, Row.A));
    }
}
