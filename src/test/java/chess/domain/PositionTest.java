package chess.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class PositionTest {

    @ParameterizedTest
    @CsvSource(value = {"a,0", "a,9", "z,1", "z,8"})
    @DisplayName("범위 외의 행,열 입력시 예외발생")
    void createPositionException(char column, char row) {
        assertThatThrownBy(() -> Position.of(column, row))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Position범위에 맞지 않는 입력값입니다.");
    }

    @ParameterizedTest
    @CsvSource(value = {"b,2,false", "b,1,true", "a,2,true"})
    @DisplayName("행 또는 열 일치 여부 확인")
    void equalsColumnOrRow(char row, char col, boolean expected) {
        Position position = Position.of('a', '1');
        Position otherPosition = Position.of(row, col);

        assertThat(position.equalsColumnOrRow(otherPosition)).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource(value = {"b,2,false", "b,1,false", "a,2,true"})
    @DisplayName("열 일치 여부 확인")
    void equalsColumn(char row, char col, boolean expected) {
        Position position = Position.of('a', '1');
        Position otherPosition = Position.of(row, col);

        assertThat(position.equalsColumn(otherPosition)).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource(value = {"a,2,1", "b,2,2", "c,4,5"})
    @DisplayName("거리 계산")
    void calculateDistance(char row, char col, int expected) {
        Position position = Position.of('a', '1');
        Position otherPosition = Position.of(row, col);

        assertThat(position.calculateDistance(otherPosition)).isEqualTo(expected);
    }

    @Test
    @DisplayName("입력받은 거리만큼 이동")
    void move() {
        Position actual = Position.of('a', '1').move(1, 1);
        Position expected = Position.of('b', '2');

        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource(value = {"1,1,true", "-1,-1,false", "1,-1,false", "-1,1,false"})
    @DisplayName("입력받은 거리만큼 이동 가능 여부 확인")
    void movable(int columnAmount, int rowAmount, boolean expected) {
        Position position = Position.of('a', '1');

        assertThat(position.isMovable(columnAmount, rowAmount)).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource(value = {"a,1,true", "b,2,false", "h,8,true"})
    @DisplayName("프로모션 위치인지 확인")
    void isPromotionPosition(char column, char row, boolean expected) {
        Position position = Position.of(column, row);
        assertThat(position.isPromotionPosition()).isEqualTo(expected);
    }
}
