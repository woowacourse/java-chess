package chess.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
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
    @CsvSource(value = {"b,2,false", "b,1,true", "a,2,true"})
    @DisplayName("행 또는 열 일치 여부 확인")
    void equalsColumnOrRow(char row, char col, boolean expected) {
        Position position = new Position('a', '1');
        Position otherPosition = new Position(row, col);

        assertThat(position.equalsColumnOrRow(otherPosition)).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource(value = {"a,2,1", "b,2,2", "c,4,5"})
    @DisplayName("거리 계산")
    void calculateDistance(char row, char col, int expected) {
        Position position = new Position('a', '1');
        Position otherPosition = new Position(row, col);

        assertThat(position.calculateDistance(otherPosition)).isEqualTo(expected);
    }

    @Test
    @DisplayName("입력받은 거리만큼 이동")
    void move() {
        Position actual = new Position('a', '1').move(1, 1);
        Position expected = new Position('b', '2');

        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource(value = {"1,1,true", "-1,-1,false", "1,-1,false", "-1,1,false"})
    @DisplayName("입력받은 거리만큼 이동 가능 여부 확인")
    void movable(int columnAmount, int rowAmount, boolean expected) {
        Position position = new Position('a', '1');

        assertThat(position.isMovable(columnAmount, rowAmount)).isEqualTo(expected);
    }
}
