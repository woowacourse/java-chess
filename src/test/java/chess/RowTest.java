package chess;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("행")
class RowTest {

    @DisplayName("8은 맨 위다.")
    @Test
    void isTop_8() {
        assertThat(Row.EIGHT.isTop()).isTrue();
    }

    @DisplayName("7은 맨 위가 아니다.")
    @Test
    void isTop_7() {
        assertThat(Row.SEVEN.isTop()).isFalse();
    }

    @DisplayName("1은 맨 아래다.")
    @Test
    void isBottom_1() {
        assertThat(Row.ONE.isBottom()).isTrue();
    }

    @DisplayName("2는 맨 아래가 아니다.")
    @Test
    void isBottom_2() {
        assertThat(Row.TWO.isBottom()).isFalse();
    }

    @DisplayName("8은 위로 이동할 수 없다.")
    @Test
    void moveUp_8() {
        assertThatThrownBy(Row.EIGHT::moveUp)
                .isInstanceOf(IllegalStateException.class);
    }

    @DisplayName("7은 위로 이동하면 8이다.")
    @Test
    void moveUp_7() {
        final var moved = Row.SEVEN.moveUp();

        assertThat(moved).isEqualTo(Row.EIGHT);
    }

    @DisplayName("7은 위로 2번 이동할 수 없다.")
    @Test
    void moveUp_2_7() {
        assertThatThrownBy(() -> Row.SEVEN.moveUp(2))
                .isInstanceOf(IllegalStateException.class);
    }

    @DisplayName("6은 위로 2번 이동하면 8이다.")
    @Test
    void moveUp_2_6() {
        final var moved = Row.SIX.moveUp(2);

        assertThat(moved).isEqualTo(Row.EIGHT);
    }

    @DisplayName("1은 아래로 이동할 수 없다.")
    @Test
    void moveDown_1() {
        assertThatThrownBy(Row.ONE::moveDown)
                .isInstanceOf(IllegalStateException.class);
    }

    @DisplayName("2는 아래로 이동하면 1이다.")
    @Test
    void moveDown_2() {
        final var moved = Row.TWO.moveDown();

        assertThat(moved).isEqualTo(Row.ONE);
    }

    @DisplayName("2는 아래로 2번 이동할 수 없다.")
    @Test
    void moveDown_2_2() {
        assertThatThrownBy(() -> Row.TWO.moveDown(2))
                .isInstanceOf(IllegalStateException.class);
    }

    @DisplayName("3은 아래로 2번 이동하면 1이다.")
    @Test
    void moveDown_2_3() {
        final var moved = Row.THREE.moveDown(2);

        assertThat(moved).isEqualTo(Row.ONE);
    }
}
