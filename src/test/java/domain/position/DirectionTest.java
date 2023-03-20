package domain.position;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

class DirectionTest {

    @DisplayName("8가지 방향을 검증할 수 있다")
    @ParameterizedTest
    @CsvSource({"B6,NW", "D6,N", "F6,NE", "F4,E", "F2,SE", "D2,S", "B2,SW", "B4,W"})
    void of(String to, String expected) {
        //given
        final Position source = Positions.from("D4");
        final Position destination = Positions.from(to);
        final Direction direction = Direction.valueOf(expected);

        //when

        //then
        assertThat(Direction.of(source, destination)).isEqualTo(direction);
    }

    @DisplayName("8가지 방향이 아니면 NOTHING을 리턴한다")
    @Test
    void of() {
        //given
        final Position source = Positions.from("D4");
        final Position destination = Positions.from("F5");

        //when

        //then
        assertThat(Direction.of(source, destination)).isEqualTo(Direction.NOTHING);
    }

    @DisplayName("대각선 관계면 true를 반환한다")
    @ParameterizedTest
    @ValueSource(strings = {"B6", "F6", "B2", "F2"})
    void isDiagonal(String to) {
        //given
        final Position source = Positions.from("D4");
        final Position destination = Positions.from(to);

        //when

        //then
        assertThat(Direction.isDiagonal(source, destination)).isTrue();
    }

    @DisplayName("대각선 관계가 아니면 false를 반환한다")
    @ParameterizedTest
    @ValueSource(strings = {"B7", "F5", "C2", "H2"})
    void isNotDiagonal(String to) {
        //given
        final Position source = Positions.from("D4");
        final Position destination = Positions.from(to);

        //when

        //then
        assertThat(Direction.isDiagonal(source, destination)).isFalse();
    }

    @DisplayName("직선 관계면 true를 반환한다")
    @ParameterizedTest
    @ValueSource(strings = {"D8", "D1", "A4", "F4"})
    void isStraight(String to) {
        //given
        final Position source = Positions.from("D4");
        final Position destination = Positions.from(to);

        //when

        //then
        assertThat(Direction.isStraight(source, destination)).isTrue();
    }

    @DisplayName("직선 관계가 아니면 false를 반환한다")
    @ParameterizedTest
    @ValueSource(strings = {"H8", "B1", "A2", "F7"})
    void isNotStraight(String to) {
        //given
        final Position source = Positions.from("D4");
        final Position destination = Positions.from(to);

        //when

        //then
        assertThat(Direction.isStraight(source, destination)).isFalse();
    }
}
