package chess.domain.position;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static chess.domain.position.Fixtures.*;
import static org.assertj.core.api.Assertions.*;

class PositionTest {

    @ParameterizedTest
    @EnumSource(value = Column.class)
    void of_Returns_SameInstance_When_HasEqualValue(Column column) {
        Row row = Row.ONE;
        assertThat(Position.of(column, row)).isEqualTo(Position.of(column, row));
    }

    @Test
    void of_DoesNotThrowException_When_CreatedByString() {
        assertThatCode(() -> Position.of("A1"))
                .doesNotThrowAnyException();
    }

    @Test
    void up_When_Success() {
        Position actual = A1.up();

        assertThat(actual).isEqualTo(A2);
    }

    @Test
    void up_When_Fail() {
        assertThatThrownBy(H8::up)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("9 이상의 row 값은 가질수 없습니다.");
    }

    @Test
    void down_When_Success() {
        Position actual = A2.down();

        assertThat(actual).isEqualTo(A1);
    }

    @Test
    void down_When_Fail() {
        assertThatThrownBy(A1::down)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("0 이하의 row 값은 가질수 없습니다.");
    }

    @Test
    void right_When_Success() {
        Position actual = A1.right();

        assertThat(actual).isEqualTo(B1);
    }

    @Test
    void right_When_Fail() {
        assertThatThrownBy(H3::right)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("9 이상의 column 값은 가질수 없습니다.");
    }

    @Test
    void left_When_Success() {
        Position actual = B1.left();

        assertThat(actual).isEqualTo(A1);
    }

    @Test
    void left_When_Fail() {
        assertThatThrownBy(A1::left)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("0 이하의 column 값은 가질수 없습니다.");
    }

    @Test
    void columnGap() {
        assertThat(A1.getColumnGap(B2)).isEqualTo(-1);
    }

    @Test
    void rowGap() {
        assertThat(A1.getRowGap(A2)).isEqualTo(-1);
    }
}