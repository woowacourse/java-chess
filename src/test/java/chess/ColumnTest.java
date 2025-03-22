package chess;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("열")
class ColumnTest {

    @DisplayName("A는 맨 왼쪽이다.")
    @Test
    void isFarLeft_A() {
        assertThat(Column.A.isFarLeft()).isTrue();
    }

    @DisplayName("B는 맨 왼쪽이 아니다.")
    @Test
    void isFarLeft_B() {
        assertThat(Column.B.isFarLeft()).isFalse();
    }

    @DisplayName("H는 맨 오른쪽이다.")
    @Test
    void isFarRight_H() {
        assertThat(Column.H.isFarRight()).isTrue();
    }

    @DisplayName("F는 맨 오른쪽이 아니다.")
    @Test
    void isFarRight_F() {
        assertThat(Column.F.isFarRight()).isFalse();
    }

    @DisplayName("A는 왼쪽으로 이동할 수 없다.")
    @Test
    void moveLeft_A() {
        assertThatThrownBy(Column.A::moveLeft)
                .isInstanceOf(IllegalStateException.class);
    }

    @DisplayName("B는 왼쪽으로 이동하면 A다.")
    @Test
    void moveLeft_B() {
        final var moved = Column.B.moveLeft();

        assertThat(moved).isEqualTo(Column.A);
    }

    @DisplayName("B는 왼쪽으로 2번 이동할 수 없다.")
    @Test
    void moveLeft_2_B() {
        assertThatThrownBy(() -> Column.B.moveLeft(2))
                .isInstanceOf(IllegalStateException.class);
    }

    @DisplayName("C는 왼쪽으로 2번 이동하면 A다.")
    @Test
    void moveLeft_2_C() {
        final var moved = Column.C.moveLeft(2);

        assertThat(moved).isEqualTo(Column.A);
    }

    @DisplayName("H는 오른쪽으로 이동할 수 없다.")
    @Test
    void moveRight_H() {
        assertThatThrownBy(Column.H::moveRight)
                .isInstanceOf(IllegalStateException.class);
    }

    @DisplayName("G는 오른쪽으로 이동하면 H다.")
    @Test
    void moveRight_G() {
        final var moved = Column.G.moveRight();

        assertThat(moved).isEqualTo(Column.H);
    }

    @DisplayName("G는 오른쪽으로 2번 이동할 수 없다.")
    @Test
    void moveRight_2_G() {
        assertThatThrownBy(() -> Column.G.moveRight(2))
                .isInstanceOf(IllegalStateException.class);
    }

    @DisplayName("F는 오른쪽으로 2번 이동하면 H다.")
    @Test
    void moveRight_2_F() {
        final var moved = Column.F.moveRight(2);

        assertThat(moved).isEqualTo(Column.H);
    }
}
