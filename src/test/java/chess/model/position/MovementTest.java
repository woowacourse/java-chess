package chess.model.position;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class MovementTest {

    @ParameterizedTest
    @MethodSource(value = "provideSideAndExpectedResult")
    @DisplayName("진영 위치에 따라 직진 움직임인지 판단한다.")
    void isForward(boolean isUpperSide, Movement movement, boolean expectedResult) {
        // when
        boolean actualResult = movement.isForward(isUpperSide);

        // then
        assertThat(actualResult).isEqualTo(expectedResult);
    }

    private static Stream<Arguments> provideSideAndExpectedResult() {
        return Stream.of(
                Arguments.of(false, new Movement(Difference.from(0), Difference.from(1)), true),
                Arguments.of(false, new Movement(Difference.from(0), Difference.from(-1)), false),
                Arguments.of(true, new Movement(Difference.from(0), Difference.from(1)), false),
                Arguments.of(true, new Movement(Difference.from(0), Difference.from(-1)), true),
                Arguments.of(true, new Movement(Difference.from(1), Difference.from(0)), false)
        );
    }

    @ParameterizedTest
    @MethodSource(value = "provideMovementAndDiagonalResult")
    @DisplayName("대각선 움직임인지 판단한다.")
    void isDiagonal(Movement movement, boolean expectedResult) {
        // when
        boolean actualResult = movement.isDiagonal();

        // then
        assertThat(actualResult).isEqualTo(expectedResult);
    }

    private static Stream<Arguments> provideMovementAndDiagonalResult() {
        return Stream.of(
                Arguments.of(new Movement(Difference.from(1), Difference.from(1)), true),
                Arguments.of(new Movement(Difference.from(1), Difference.from(-1)), true),
                Arguments.of(new Movement(Difference.from(0), Difference.from(1)), false)
        );
    }

    @Test
    @DisplayName("제자리에 있으면 대각선 움직임이 아니다.")
    void isDiagonalWhenStay() {
        // given
        Difference fileDifference = Difference.from(0);
        Difference rankDifference = Difference.from(0);
        Movement movement = new Movement(fileDifference, rankDifference);

        // when
        boolean result = movement.isDiagonal();

        // then
        assertThat(result).isFalse();
    }

    @ParameterizedTest
    @MethodSource(value = "provideMovementAndOrthogonalResult")
    @DisplayName("직교 움직임인지 판단한다.")
    void isOrthogonal(Movement movement, boolean expectedResult) {
        // when
        boolean result = movement.isOrthogonal();

        // then
        assertThat(result).isEqualTo(expectedResult);
    }

    private static Stream<Arguments> provideMovementAndOrthogonalResult() {
        return Stream.of(
                Arguments.of(new Movement(Difference.from(0), Difference.from(1)), true),
                Arguments.of(new Movement(Difference.from(0), Difference.from(4)), true),
                Arguments.of(new Movement(Difference.from(1), Difference.from(0)), true),
                Arguments.of(new Movement(Difference.from(-5), Difference.from(0)), true),
                Arguments.of(new Movement(Difference.from(2), Difference.from(4)), false)
        );
    }

    @Test
    @DisplayName("제자리에 있으면 직교 움직임이 아니다.")
    void isOrthogonalWhenStay() {
        // given
        Difference fileDifference = Difference.from(0);
        Difference rankDifference = Difference.from(0);
        Movement movement = new Movement(fileDifference, rankDifference);

        // when
        boolean result = movement.isOrthogonal();

        // then
        assertThat(result).isFalse();
    }

    @ParameterizedTest
    @MethodSource(value = "provideMovementAndLengthAndExpectedResult")
    @DisplayName("움직인 거리를 구한다.")
    void hasSameLengthOf(Movement movement, int expectedLength) {
        // when
        int actualLength = movement.calculateLength();

        // then
        assertThat(actualLength).isEqualTo(expectedLength);
    }

    private static Stream<Arguments> provideMovementAndLengthAndExpectedResult() {
        return Stream.of(
                Arguments.of(new Movement(Difference.from(3), Difference.from(0)), 3),
                Arguments.of(new Movement(Difference.from(2), Difference.from(-2)), 2),
                Arguments.of(new Movement(Difference.from(0), Difference.from(5)), 5),
                Arguments.of(new Movement(Difference.from(1), Difference.from(-1)), 1),
                Arguments.of(new Movement(Difference.from(3), Difference.from(5)), 8),
                Arguments.of(new Movement(Difference.from(0), Difference.from(0)), 0)
        );
    }

    @ParameterizedTest
    @CsvSource(value = {"-5,-1", "4,1", "0,0"})
    @DisplayName("File 움직임의 증가량을 구한다.")
    void calculateFileIncrement(int fileDifference, int expectedIncrement) {
        // given
        Movement movement = new Movement(Difference.from(fileDifference), Difference.from(0));

        // when
        int actualIncrement = movement.calculateFileIncrement();

        // then
        assertThat(actualIncrement).isEqualTo(expectedIncrement);
    }

    @ParameterizedTest
    @CsvSource(value = {"-5,-1", "4,1", "0,0"})
    @DisplayName("Rank 움직임의 증가량을 구한다.")
    void calculateRankIncrement(int rankDifference, int expectedIncrement) {
        // given
        Movement movement = new Movement(Difference.from(0), Difference.from(rankDifference));

        // when
        int actualIncrement = movement.calculateRankIncrement();

        // then
        assertThat(actualIncrement).isEqualTo(expectedIncrement);
    }
}
