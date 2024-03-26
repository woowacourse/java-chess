package model.position;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class MovingTest {

    @DisplayName("같은 위치인지 확인한다.")
    @ParameterizedTest
    @ValueSource(strings = {"a2", "b4", "h8"})
    void checkIsNotMovedTrue(String value) {
        //given
        final Position current = Position.from(value);
        final Position next = Position.from(value);

        //when
        final Moving moving = new Moving(current, next);

        //then
        assertThat(moving.isNotMoved()).isTrue();
    }

    @DisplayName("다른 위치인지 확인한다.")
    @ParameterizedTest
    @CsvSource(value = {"a1,a2", "b5,b3", "d8,g4"})
    void checkIsNotMovedFalse(String current, String next) {
        //given
        final Position currentPosition = Position.from(current);
        final Position nextPosition = Position.from(next);

        //when
        final Moving moving = new Moving(currentPosition, nextPosition);

        //then
        assertThat(moving.isNotMoved()).isFalse();
    }

    @DisplayName("수평인지 확인한다.")
    @ParameterizedTest
    @CsvSource(value = {"b2,f2", "h6,a6", "d3,c3"})
    void checkHorizontal(String current, String next) {
        //given
        final Position currentPosition = Position.from(current);
        final Position nextPosition = Position.from(next);

        //when
        final Moving moving = new Moving(currentPosition, nextPosition);

        //then
        assertThat(moving.isHorizontal()).isTrue();
    }

    @DisplayName("수직인지 확인한다.")
    @ParameterizedTest
    @CsvSource(value = {"a7,a1", "e2,e5", "g1,g8"})
    void checkVertical(String current, String next) {
        //given
        final Position currentPosition = Position.from(current);
        final Position nextPosition = Position.from(next);

        //when
        final Moving moving = new Moving(currentPosition, nextPosition);

        //then
        assertThat(moving.isVertical()).isTrue();
    }

    @DisplayName("대각선인지 확인한다.")
    @ParameterizedTest
    @CsvSource(value = {"b8,g3", "h7,f5", "e1,d2", "a1,h8"})
    void checkDiagonal(String current, String next) {
        //given
        final Position currentPosition = Position.from(current);
        final Position nextPosition = Position.from(next);

        //when
        final Moving moving = new Moving(currentPosition, nextPosition);

        //then
        assertThat(moving.isDiagonal()).isTrue();
    }
}
