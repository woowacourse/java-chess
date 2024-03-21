package model.position;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class MovingTest {

    @ParameterizedTest
    @DisplayName("같은 위치라면 true를 반환한다.")
    @ValueSource(strings = {"a2", "b4", "h8"})
    void isNotMoved(String value) {
        //given
        Position current = Position.from(value);
        Position next = Position.from(value);

        //when
        Moving moving = new Moving(current, next);

        //then
        assertThat(moving.isNotMoved()).isTrue();
    }

    @ParameterizedTest
    @DisplayName("다른 위치면 false를 반환한다.")
    @CsvSource(value = {"a1,a2", "b5,b3", "d8,g4"})
    void isMoved(String current, String next) {
        //given
        Position currentPosition = Position.from(current);
        Position nextPosition = Position.from(next);

        //when
        Moving moving = new Moving(currentPosition, nextPosition);

        //then
        assertThat(moving.isNotMoved()).isFalse();
    }

    @ParameterizedTest
    @DisplayName("수평이면 true를 반환한다.")
    @CsvSource(value = {"b2,f2", "h6,a6", "d3,c3"})
    void checkHorizontal(String current, String next) {
        //given
        Position currentPosition = Position.from(current);
        Position nextPosition = Position.from(next);

        //when
        Moving moving = new Moving(currentPosition, nextPosition);

        //then
        assertThat(moving.isHorizontal()).isTrue();
    }

    @ParameterizedTest
    @DisplayName("수직이면 true를 반환한다.")
    @CsvSource(value = {"a7,a1", "e2,e5", "g1,g8"})
    void checkVertical(String current, String next) {
        //given
        Position currentPosition = Position.from(current);
        Position nextPosition = Position.from(next);

        //when
        Moving moving = new Moving(currentPosition, nextPosition);

        //then
        assertThat(moving.isVertical()).isTrue();
    }

    @ParameterizedTest
    @DisplayName("대각선이면 true를 반환한다.")
    @CsvSource(value = {"b8,g3", "h7,f5", "e1,d2", "a1,h8"})
    void checkDiagonal(String current, String next) {
        //given
        Position currentPosition = Position.from(current);
        Position nextPosition = Position.from(next);

        //when
        Moving moving = new Moving(currentPosition, nextPosition);

        //then
        assertThat(moving.isDiagonal()).isTrue();
    }
}
