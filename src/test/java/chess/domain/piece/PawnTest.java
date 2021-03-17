package chess.domain.piece;

import chess.domain.Side;
import chess.domain.position.Position;
import chess.exception.InvalidMovementException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PawnTest {
    private static Piece pawnBlack;
    private static Piece pawnWhite;

    @BeforeEach
    void setUp() {
        pawnBlack = new Pawn(Side.BLACK);
        pawnWhite = new Pawn(Side.WHITE);
    }


    @ParameterizedTest(name = "백 진영 Pawn 이동 성공")
    @MethodSource("routeWhitePawnSuccessTestcase")
    void routeWhitePawnSuccess(Position to) {
        assertThat(pawnWhite.route(Position.of("b2"), to)).isEmpty();
    }

    private static Stream<Arguments> routeWhitePawnSuccessTestcase() {
        return Stream.of(
                Arguments.of(Position.of("a3")),
                Arguments.of(Position.of("b3")),
                Arguments.of(Position.of("c3"))
        );
    }

    @ParameterizedTest(name = "흑 진영 Pawn 이동 성공")
    @MethodSource("routeBlackPawnSuccessTestcase")
    void routeBlackPawnSuccess(Position to) {
        assertThat(pawnBlack.route(Position.of("b7"), to)).isEmpty();
    }

    private static Stream<Arguments> routeBlackPawnSuccessTestcase() {
        return Stream.of(
                Arguments.of(Position.of("a6")),
                Arguments.of(Position.of("b6")),
                Arguments.of(Position.of("c6"))
        );
    }

    @ParameterizedTest(name = "백 진영 Pawn 이동 실패")
    @MethodSource("routeWhitePawnFailTestcase")
    void routeWhitePawnFail(Position to) {
        assertThatThrownBy(() -> pawnWhite.route(Position.of("b2"), to))
                .isInstanceOf(InvalidMovementException.class);
    }

    private static Stream<Arguments> routeWhitePawnFailTestcase() {
        return Stream.of(
                Arguments.of(Position.of("b1")),
                Arguments.of(Position.of("a1")),
                Arguments.of(Position.of("a2")),
                Arguments.of(Position.of("b5")),
                Arguments.of(Position.of("b2"))
        );
    }

    @ParameterizedTest(name = "흑 진영 Pawn 이동 실패")
    @MethodSource("routeBlackPawnFailTestcase")
    void routeBlackPawnFail(Position to) {
        assertThatThrownBy(() -> pawnBlack.route(Position.of("b7"), to))
                .isInstanceOf(InvalidMovementException.class);
    }

    private static Stream<Arguments> routeBlackPawnFailTestcase() {
        return Stream.of(
                Arguments.of(Position.of("b8")),
                Arguments.of(Position.of("c8")),
                Arguments.of(Position.of("c7")),
                Arguments.of(Position.of("b4")),
                Arguments.of(Position.of("b7"))
        );
    }

    @Test
    void moveDoubleSuccess() {
        List<Position> route1 = pawnBlack.route(Position.of("b7"), Position.of("b5"));
        assertThat(route1).containsExactly(Position.of("b6"));

        List<Position> route2 = pawnWhite.route(Position.of("b2"), Position.of("b4"));
        assertThat(route2).containsExactly(Position.of("b3"));
    }

    @Test
    void moveDoubleFail() {
        pawnBlack.moved();
        assertThatThrownBy(() -> pawnBlack.route(Position.of("b6"), Position.of("b4")))
                .isInstanceOf(InvalidMovementException.class);

        pawnWhite.moved();
        assertThatThrownBy(() -> pawnWhite.route(Position.of("b3"), Position.of("b5")))
                .isInstanceOf(InvalidMovementException.class);
    }
}