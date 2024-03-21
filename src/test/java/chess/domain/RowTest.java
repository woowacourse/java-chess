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

class RowTest {

    @Test
    @DisplayName("거리 만큼 이동한 Row를 반환한다.")
    void calculateNextRowSuccessTest() {
        Row row = Row.RANK4;

        assertAll(
            () -> assertEquals(Row.RANK5, row.calculateNextRow(-1)),
            () -> assertEquals(Row.RANK3, row.calculateNextRow(1)),
            () -> assertEquals(Row.RANK4, row.calculateNextRow(0))
        );
    }

    @Test
    @DisplayName("거리 만큼 이동한 값이 보드판을 벗어난 경우 에러를 반환한다.")
    void calculateNextRowFailTest() {
        Row row = Row.RANK4;

        assertThatThrownBy(() -> row.calculateNextRow(-5))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("이름으로 Row를 찾는다.")
    void findByNameSuccessTest() {
        assertAll(
            () -> assertEquals(Row.RANK1, Row.findByName("1")),
            () -> assertEquals(Row.RANK2, Row.findByName("2")),
            () -> assertEquals(Row.RANK3, Row.findByName("3")),
            () -> assertEquals(Row.RANK4, Row.findByName("4")),
            () -> assertEquals(Row.RANK5, Row.findByName("5")),
            () -> assertEquals(Row.RANK6, Row.findByName("6")),
            () -> assertEquals(Row.RANK7, Row.findByName("7")),
            () -> assertEquals(Row.RANK8, Row.findByName("8"))
        );
    }

    @ParameterizedTest
    @ValueSource(strings = {"   ", "zxc", "123"})
    @DisplayName("이름으로 Row를 찾는데 실패한다.")
    void findByNameFailTest(String value) {
        assertThatThrownBy(() -> Row.findByName(value))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("거리 만큼 이동한 값이 보드판을 벗어난 경우 false를 반환한다.")
    void isNextInRange() {
        assertAll(
            () -> assertTrue(Row.RANK4.isNextInRange(-1)),
            () -> assertTrue(Row.RANK4.isNextInRange(1)),
            () -> assertFalse(Row.RANK8.isNextInRange(-1)),
            () -> assertFalse(Row.RANK1.isNextInRange(1))
        );
    }
}
