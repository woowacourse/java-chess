package chess.domain;

import static chess.domain.Color.BLACK;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

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
    @ValueSource(strings = {"a", "aa1"})
    @DisplayName("from 2글자 입력 아닐때 예외발생")
    void createPositionFormatException(String position) {
        assertThatThrownBy(() -> Position.from(position))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Position format은 2글자입니다.");
    }

    @Test
    @DisplayName("2글자 position받아 생성")
    void createPositionFormat() {
        assertThat(Position.from("a1")).isEqualTo(Position.of('a', '1'));
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
    @CsvSource(value = {"a,1,true", "h,8,false"})
    @DisplayName("프로모션 위치인지 확인")
    void isPromotionPosition(char column, char row, boolean expected) {
        Position position = Position.of(column, row);
        assertThat(position.isPromotionPosition(BLACK)).isEqualTo(expected);
    }
}
