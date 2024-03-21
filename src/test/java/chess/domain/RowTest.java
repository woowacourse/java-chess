package chess.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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
