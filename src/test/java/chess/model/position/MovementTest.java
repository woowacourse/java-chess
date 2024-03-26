package chess.model.position;

import chess.model.piece.Side;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class MovementTest {

    @ParameterizedTest
    @MethodSource(value = "provideSideAndExpectedResult")
    @DisplayName("진영에 따라 직진 움직임인지 판단한다.")
    void isForward(Side side, Movement movement, boolean expectedResult) {
        // when
        boolean actualResult = movement.isForward(side);

        // then
        assertThat(actualResult).isEqualTo(expectedResult);
    }

    private static Stream<Arguments> provideSideAndExpectedResult() {
        return Stream.of(
                Arguments.of(Side.WHITE, new Movement(Difference.from(0), Difference.from(1)), true),
                Arguments.of(Side.WHITE, new Movement(Difference.from(0), Difference.from(-1)), false),
                Arguments.of(Side.BLACK, new Movement(Difference.from(0), Difference.from(1)), false),
                Arguments.of(Side.BLACK, new Movement(Difference.from(0), Difference.from(-1)), true),
                Arguments.of(Side.BLACK, new Movement(Difference.from(1), Difference.from(0)), false)
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
    @DisplayName("같은 거리인지 판단한다.")
    void hasSameLengthOf(Movement movement, int length, boolean expectedResult) {
        // when
        boolean result = movement.hasLengthOf(length);

        // then
        assertThat(result).isEqualTo(expectedResult);
    }

    private static Stream<Arguments> provideMovementAndLengthAndExpectedResult() {
        return Stream.of(
                Arguments.of(new Movement(Difference.from(3), Difference.from(0)), 3, true),
                Arguments.of(new Movement(Difference.from(2), Difference.from(-2)), 2, true),
                Arguments.of(new Movement(Difference.from(0), Difference.from(5)), 1, false),
                Arguments.of(new Movement(Difference.from(1), Difference.from(-1)), 4, false),
                Arguments.of(new Movement(Difference.from(3), Difference.from(5)), 3, false)
        );
    }
}
