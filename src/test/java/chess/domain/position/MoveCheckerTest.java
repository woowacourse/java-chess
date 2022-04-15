package chess.domain.position;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.player.Team;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Named;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class MoveCheckerTest {

    @ParameterizedTest(name = "{2}")
    @MethodSource("provideLinearPosition")
    @DisplayName("상하좌우로 이동하는지 확인한다.")
    void checkLinear(final Position current, final Position destination, final boolean expected) {
        final boolean actual = MoveChecker.isLinear(current, destination);

        assertThat(actual).isEqualTo(expected);
    }

    static Stream<Arguments> provideLinearPosition() {
        return Stream.of(
                Arguments.of(new Position(3, 'b'), new Position(4, 'b'), Named.of("상", true)),
                Arguments.of(new Position(3, 'b'), new Position(2, 'b'), Named.of("하", true)),
                Arguments.of(new Position(3, 'b'), new Position(3, 'a'), Named.of("좌", true)),
                Arguments.of(new Position(3, 'b'), new Position(3, 'c'), Named.of("우", true)),
                Arguments.of(new Position(3, 'b'), new Position(4, 'c'), Named.of("대각선 불가", false))
        );
    }

    @ParameterizedTest(name = "{2}")
    @MethodSource("provideDiagonalPosition")
    @DisplayName("대각선으로 이동하는지 확인한다.")
    void checkDiagonal(final Position current, final Position destination, final boolean expected) {
        final boolean actual = MoveChecker.isDiagonal(current, destination);

        assertThat(actual).isEqualTo(expected);
    }

    static Stream<Arguments> provideDiagonalPosition() {
        return Stream.of(
                Arguments.of(new Position(3, 'b'), new Position(4, 'c'), Named.of("↗", true)),
                Arguments.of(new Position(3, 'b'), new Position(4, 'a'), Named.of("↖", true)),
                Arguments.of(new Position(3, 'b'), new Position(2, 'c'), Named.of("↘", true)),
                Arguments.of(new Position(3, 'b'), new Position(2, 'a'), Named.of("↙", true)),
                Arguments.of(new Position(3, 'b'), new Position(4, 'b'), Named.of("상 불가능", false))
        );
    }

    @ParameterizedTest(name = "{2}")
    @MethodSource("provideKnightPosition")
    @DisplayName("나이트의 이동인지 확인한다.")
    void checkKnight(final Position current, final Position destination, final boolean expected) {
        final boolean actual = MoveChecker.isForKnight(current, destination);

        assertThat(actual).isEqualTo(expected);
    }

    static Stream<Arguments> provideKnightPosition() {
        return Stream.of(
                Arguments.of(new Position(4, 'd'), new Position(6, 'e'), Named.of("상↗", true)),
                Arguments.of(new Position(4, 'd'), new Position(6, 'c'), Named.of("상↖", true)),
                Arguments.of(new Position(4, 'd'), new Position(2, 'e'), Named.of("하↘", true)),
                Arguments.of(new Position(4, 'd'), new Position(2, 'c'), Named.of("하↙", true)),
                Arguments.of(new Position(4, 'd'), new Position(5, 'b'), Named.of("좌↖", true)),
                Arguments.of(new Position(4, 'd'), new Position(3, 'b'), Named.of("좌↙", true)),
                Arguments.of(new Position(4, 'd'), new Position(5, 'f'), Named.of("우↗", true)),
                Arguments.of(new Position(4, 'd'), new Position(3, 'f'), Named.of("우↘", true)),
                Arguments.of(new Position(3, 'b'), new Position(4, 'b'), Named.of("상 불가능", false))
        );
    }

    @ParameterizedTest(name = "{2}")
    @MethodSource("provideForwardPosition")
    @DisplayName("앞으로 이동하는지 확인한다.")
    void checkForward(final Position current, final Position destination, final boolean expected) {
        final boolean actual = MoveChecker.isForward(current, destination, Team.WHITE);

        assertThat(actual).isEqualTo(expected);
    }

    static Stream<Arguments> provideForwardPosition() {
        return Stream.of(
                Arguments.of(new Position(3, 'b'), new Position(4, 'b'), Named.of("앞으로 1칸", true)),
                Arguments.of(new Position(3, 'b'), new Position(5, 'b'), Named.of("앞으로 2칸", true)),
                Arguments.of(new Position(3, 'b'), new Position(2, 'b'), Named.of("뒤로 이동 불가능", false))
        );
    }

    @ParameterizedTest(name = "{2}")
    @MethodSource("provideDiagonalForwardPosition")
    @DisplayName("대각선 앞으로만 이동하는지 확인한다.")
    void checkDiagonalForward(final Position current, final Position destination, final boolean expected) {
        final boolean actual = MoveChecker.isDiagonalForward(current, destination, Team.WHITE);

        assertThat(actual).isEqualTo(expected);
    }

    static Stream<Arguments> provideDiagonalForwardPosition() {
        return Stream.of(
                Arguments.of(new Position(3, 'b'), new Position(4, 'c'), Named.of("↗", true)),
                Arguments.of(new Position(3, 'b'), new Position(4, 'a'), Named.of("↖", true)),
                Arguments.of(new Position(3, 'b'), new Position(2, 'c'), Named.of("↘ 불가능", false)),
                Arguments.of(new Position(3, 'b'), new Position(2, 'a'), Named.of("↙ 불가능", false))
        );
    }
}
