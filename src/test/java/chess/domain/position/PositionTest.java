package chess.domain.position;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

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

    @Test
    @DisplayName("동, 서, 남, 북으로 이동한 위치를 알 수 있다.")
    void moveTest() {
        Position position = new Position(Row.FOUR, Column.D);

        assertAll(
                () -> assertThat(position.moveToEast()).isEqualTo(new Position(Row.FOUR, Column.E)),
                () -> assertThat(position.moveToWest()).isEqualTo(new Position(Row.FOUR, Column.C)),
                () -> assertThat(position.moveToSouth()).isEqualTo(new Position(Row.THREE, Column.D)),
                () -> assertThat(position.moveToNorth()).isEqualTo(new Position(Row.FIVE, Column.D))
        );
    }
}
