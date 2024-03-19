package chess;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PositionTest {

    @Test
    @DisplayName("같은 행, 같은 열을 가르키면 같은 객체라고 판단한다.")
    void equalsTest() {
        Position one = new Position(Row.SIX, Column.A);
        Position another = new Position(Row.SIX, Column.A);
        Position different = new Position(Row.SIX, Column.C);

        assertThat(one)
                .isEqualTo(another)
                .isNotEqualTo(different)
                .hasSameHashCodeAs(another);
    }
}
