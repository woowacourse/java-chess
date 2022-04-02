package chess.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class PositionTest {

    @DisplayName("행 범위를 벗어난 Position 객체는 생성할 수 없습니다.")
    @ParameterizedTest
    @ValueSource(chars = {'`', 'i'})
    void columnOutOfRangeException(final char column) {
        assertThatThrownBy(() -> Position.of(column, '1'))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("열 위치는 a~h 범위에 포함되어야 합니다.");
    }

    @DisplayName("열 범위를 벗어난 Position 객체는 생성할 수 없습니다.")
    @ParameterizedTest
    @ValueSource(chars = {'0', '9'})
    void rowOutOfRangeException(final char row) {
        assertThatThrownBy(() -> Position.of('a', row))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("행 위치는 1~8 범위에 포함되어야 합니다.");
    }

    @DisplayName("Position 객체는 캐싱되어야 한다.")
    @ParameterizedTest
    @CsvSource(value = {"a,1", "a,8", "h,1", "h,8"})
    void sameInstance(final char column, final char row) {
        final Position position1 = Position.of(column, row);
        final Position position2 = Position.of(column, row);
        assertThat(position1).isSameAs(position2);
    }

    @DisplayName("Position 문자열의 길이는 2여야 한다.")
    @ParameterizedTest
    @ValueSource(strings = {"a22", "b2c", "aaa"})
    void stringOutOfLengthException(final String position) {
        assertThatThrownBy(() -> Position.from(position))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Position 문자열은 길이가 2여야 합니다.");
    }
}
