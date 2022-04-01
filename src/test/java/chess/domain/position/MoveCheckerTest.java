package chess.domain.position;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.player.Team;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class MoveCheckerTest {

    @ParameterizedTest(name = "{3}")
    @MethodSource("provideLinearPosition")
    @DisplayName("상하좌우로 이동하는지 확인한다.")
    void checkLinear(final Position current, final Position destination, final boolean expected,
            final String direction) {
        final boolean actual = MoveChecker.isLinear(current, destination);

        assertThat(actual).isEqualTo(expected);
    }

    static Stream<Arguments> provideLinearPosition() {
        return Stream.of(
                Arguments.of(new Position(3, 'b'), new Position(4, 'b'), true, "상"),
                Arguments.of(new Position(3, 'b'), new Position(2, 'b'), true, "하"),
                Arguments.of(new Position(3, 'b'), new Position(3, 'a'), true, "좌"),
                Arguments.of(new Position(3, 'b'), new Position(3, 'c'), true, "우"),
                Arguments.of(new Position(3, 'b'), new Position(4, 'c'), false, "대각선 불가능")
        );
    }

    @ParameterizedTest(name = "{3}")
    @MethodSource("provideDiagonalPosition")
    @DisplayName("대각선으로 이동하는지 확인한다.")
    void checkDiagonal(final Position current, final Position destination, final boolean expected,
            final String direction) {
        final boolean actual = MoveChecker.isDiagonal(current, destination);

        assertThat(actual).isEqualTo(expected);
    }

    static Stream<Arguments> provideDiagonalPosition() {
        return Stream.of(
                Arguments.of(new Position(3, 'b'), new Position(4, 'c'), true, "↗"),
                Arguments.of(new Position(3, 'b'), new Position(4, 'a'), true, "↖"),
                Arguments.of(new Position(3, 'b'), new Position(2, 'c'), true, "↘"),
                Arguments.of(new Position(3, 'b'), new Position(2, 'a'), true, "↙"),
                Arguments.of(new Position(3, 'b'), new Position(4, 'b'), false, "상 불가능")
        );
    }

    @ParameterizedTest(name = "{3}")
    @MethodSource("provideKnightPosition")
    @DisplayName("대각선으로 이동하는지 확인한다.")
    void checkKnight(final Position current, final Position destination, final boolean expected,
            final String direction) {
        final boolean actual = MoveChecker.isForKnight(current, destination);

        assertThat(actual).isEqualTo(expected);
    }

    static Stream<Arguments> provideKnightPosition() {
        return Stream.of(
                Arguments.of(new Position(4, 'd'), new Position(6, 'e'), true, "상↗"),
                Arguments.of(new Position(4, 'd'), new Position(6, 'c'), true, "상↖"),
                Arguments.of(new Position(4, 'd'), new Position(2, 'e'), true, "하↘"),
                Arguments.of(new Position(4, 'd'), new Position(2, 'c'), true, "하↙"),
                Arguments.of(new Position(4, 'd'), new Position(5, 'b'), true, "좌↖"),
                Arguments.of(new Position(4, 'd'), new Position(3, 'b'), true, "좌↙"),
                Arguments.of(new Position(4, 'd'), new Position(5, 'f'), true, "우↗"),
                Arguments.of(new Position(4, 'd'), new Position(3, 'f'), true, "우↘"),
                Arguments.of(new Position(3, 'b'), new Position(4, 'b'), false, "상 불가능")
        );
    }

    @ParameterizedTest(name = "{3}")
    @MethodSource("provideForwardPosition")
    @DisplayName("앞으로 이동하는지 확인한다.")
    void checkForward(final Position current, final Position destination, final boolean expected,
            final String direction) {
        final boolean actual = MoveChecker.isForward(current, destination, Team.WHITE);

        assertThat(actual).isEqualTo(expected);
    }

    static Stream<Arguments> provideForwardPosition() {
        return Stream.of(
                Arguments.of(new Position(3, 'b'), new Position(4, 'b'), true, "앞으로 1칸"),
                Arguments.of(new Position(3, 'b'), new Position(5, 'b'), true, "앞으로 2칸"),
                Arguments.of(new Position(3, 'b'), new Position(2, 'b'), false, "뒤로 불가능")
        );
    }

    @ParameterizedTest(name = "{3}")
    @MethodSource("provideDiagonalForwardPosition")
    @DisplayName("대각선 앞으로만 이동하는지 확인한다.")
    void checkDiagonalForward(final Position current, final Position destination, final boolean expected,
            final String direction) {
        final boolean actual = MoveChecker.isDiagonalForward(current, destination, Team.WHITE);

        assertThat(actual).isEqualTo(expected);
    }

    static Stream<Arguments> provideDiagonalForwardPosition() {
        return Stream.of(
                Arguments.of(new Position(3, 'b'), new Position(4, 'c'), true, "↗"),
                Arguments.of(new Position(3, 'b'), new Position(4, 'a'), true, "↖"),
                Arguments.of(new Position(3, 'b'), new Position(2, 'c'), false, "↘ 불가능"),
                Arguments.of(new Position(3, 'b'), new Position(2, 'a'), false, "↙ 불가능")
        );
    }
}
