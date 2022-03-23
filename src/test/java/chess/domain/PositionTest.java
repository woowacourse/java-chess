package chess.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

public class PositionTest {

    @ParameterizedTest
    @ValueSource(chars = {'`', 'A', 'i'})
    @DisplayName("열 위치가 범위를 벗어나면 예외발생")
    void outOfRawRangeException(char column) {
        assertThatThrownBy(() -> new Position(column, '1'))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("열 위치는 a~h 범위에 포함되어야 합니다.");
    }

    @ParameterizedTest
    @ValueSource(chars = {'a', '0', '9'})
    @DisplayName("행 위치가 범위를 벗어나면 예외발생")
    void outOfColumnRangeException(char row) {
        assertThatThrownBy(() -> new Position('a', row))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("행 위치는 1~8 범위에 포함되어야 합니다.");
    }

    @ParameterizedTest
    @CsvSource(value = {"a,2,true", "b,1,false", "b,2,false"})
    @DisplayName("열 일치 여부 확인")
    void equalsColumn(char row, char col, boolean expected) {
        Position position = new Position('a', '1');
        Position otherPosition = new Position(row, col);

        assertThat(position.equalsColumn(otherPosition)).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource(value = {"a,2,false", "b,1,true", "b,2,false"})
    @DisplayName("행 일치 여부 확인")
    void equalsRow(char row, char col, boolean expected) {
        Position position = new Position('a', '1');
        Position otherPosition = new Position(row, col);

        assertThat(position.equalsRow(otherPosition)).isEqualTo(expected);
    }
}
