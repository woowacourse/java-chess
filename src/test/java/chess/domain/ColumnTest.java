package chess.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class ColumnTest {

    @Test
    @DisplayName("거리 만큼 이동한 Column를 반환한다.")
    void calculateNextColumnSuccessTest() {
        Column column = Column.e;

        assertAll(
                () -> assertEquals(Column.d, column.calculateNextColumn(-1)),
                () -> assertEquals(Column.f, column.calculateNextColumn(1)),
                () -> assertEquals(Column.e, column.calculateNextColumn(0))
        );
    }

    @Test
    @DisplayName("거리 만큼 이동한 값이 보드판을 벗어난 경우 에러를 반환한다.")
    void calculateNextColumnFailTest() {
        Column column = Column.d;

        assertThatThrownBy(() -> column.calculateNextColumn(-5))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("이름으로 Column를 찾는다.")
    void findByNameSuccessTest() {
        assertAll(
                () -> assertEquals(Column.a, Column.findByName("a")),
                () -> assertEquals(Column.b, Column.findByName("b")),
                () -> assertEquals(Column.c, Column.findByName("c")),
                () -> assertEquals(Column.d, Column.findByName("d")),
                () -> assertEquals(Column.e, Column.findByName("e")),
                () -> assertEquals(Column.f, Column.findByName("f")),
                () -> assertEquals(Column.g, Column.findByName("g")),
                () -> assertEquals(Column.h, Column.findByName("h"))
        );
    }

    @ParameterizedTest
    @ValueSource(strings = {"   ", "zxc", "123"})
    @DisplayName("이름으로 Column를 찾는데 실패한다.")
    void findByNameFailTest(String value) {
        assertThatThrownBy(() -> Column.findByName(value))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("거리 만큼 이동한 값이 보드판을 벗어난 경우 false를 반환한다.")
    void isNextInRange() {
        assertAll(
                () -> assertFalse(Column.a.isNextInRange(-1)),
                () -> assertFalse(Column.h.isNextInRange(1)),
                () -> assertTrue(Column.a.isNextInRange(1)),
                () -> assertTrue(Column.h.isNextInRange(-1))
        );
    }
}
