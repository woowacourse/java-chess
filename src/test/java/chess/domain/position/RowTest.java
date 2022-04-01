package chess.domain.position;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class RowTest {

    @Test
    @DisplayName("Row에 1~8이 있는지 확인한다.")
    void contain() {
        assertThat(Row.values()).containsOnly(Row.ONE, Row.TWO, Row.THREE, Row.FOUR,
                Row.FIVE, Row.SIX, Row.SEVEN, Row.EIGHT);
    }

    @Test
    @DisplayName("값을 이용해 Row를 찾는다.")
    void findRow() {
        assertThat(Row.of(7)).isEqualTo(Row.SEVEN);
    }

    @Test
    @DisplayName("1~8 이외의 값이 들어오는 경우 예외를 발생시킨다.")
    void exception() {
        assertThatThrownBy(() -> Row.of(9))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 존재하지 않는 Row 값 입니다.");
    }

    @Test
    @DisplayName("Row를 1 증가시킨다.")
    void plus() {
        Row row = Row.of(5);
        assertThat(row.move(1)).isEqualTo(Row.SIX);
    }

    @Test
    @DisplayName("Row를 1 감소시킨다.")
    void minus() {
        Row row = Row.of(5);
        assertThat(row.move(-1)).isEqualTo(Row.FOUR);
    }

    @Test
    @DisplayName("Row를 1 증가 시킬 때, 경계선을 넘어가면 null을 반환한다.")
    void plusOutOfBounds() {
        Row row = Row.of(7);

        assertThat(row.move(2)).isNull();
    }

    @Test
    @DisplayName("Row를 1 감소 시킬 때, 경계선을 넘어가면 null을 반환한다.")
    void minusOutOfBounds() {
        Row row = Row.of(2);

        assertThat(row.move(-2)).isNull();
    }
}
