package chess.domain.piece;

import chess.domain.Side;
import chess.domain.position.Position;
import chess.exception.InvalidMovementException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
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
        assertThat(pawnWhite.route(Position.from("b2"), to)).isEmpty();
    }

    private static Stream<Arguments> routeWhitePawnSuccessTestcase() {
        return Stream.of(
                Arguments.of(Position.from("a3")),
                Arguments.of(Position.from("b3")),
                Arguments.of(Position.from("c3"))
        );
    }

    @ParameterizedTest(name = "흑 진영 Pawn 이동 성공")
    @MethodSource("routeBlackPawnSuccessTestcase")
    void routeBlackPawnSuccess(Position to) {
        assertThat(pawnBlack.route(Position.from("b7"), to)).isEmpty();
    }

    private static Stream<Arguments> routeBlackPawnSuccessTestcase() {
        return Stream.of(
                Arguments.of(Position.from("a6")),
                Arguments.of(Position.from("b6")),
                Arguments.of(Position.from("c6"))
        );
    }

    @ParameterizedTest(name = "백 진영 Pawn 이동 실패")
    @MethodSource("routeWhitePawnFailTestcase")
    void routeWhitePawnFail(Position to) {
        assertThatThrownBy(() -> pawnWhite.route(Position.from("b2"), to))
                .isInstanceOf(InvalidMovementException.class);
    }

    private static Stream<Arguments> routeWhitePawnFailTestcase() {
        return Stream.of(
                Arguments.of(Position.from("b1")),
                Arguments.of(Position.from("a1")),
                Arguments.of(Position.from("a2")),
                Arguments.of(Position.from("b5")),
                Arguments.of(Position.from("b2"))
        );
    }

    @ParameterizedTest(name = "흑 진영 Pawn 이동 실패")
    @MethodSource("routeBlackPawnFailTestcase")
    void routeBlackPawnFail(Position to) {
        assertThatThrownBy(() -> pawnBlack.route(Position.from("b7"), to))
                .isInstanceOf(InvalidMovementException.class);
    }

    private static Stream<Arguments> routeBlackPawnFailTestcase() {
        return Stream.of(
                Arguments.of(Position.from("b8")),
                Arguments.of(Position.from("c8")),
                Arguments.of(Position.from("c7")),
                Arguments.of(Position.from("b4")),
                Arguments.of(Position.from("b7"))
        );
    }

    @Test
    @DisplayName("한번도 움직이지 않은 Pawn 두칸 이동, 경로 반환")
    void moveDoubleSuccess() {
        List<Position> blackPawnRoute = pawnBlack.route(Position.from("b7"), Position.from("b5"));
        assertThat(blackPawnRoute).containsExactly(Position.from("b6"));

        List<Position> whitePawnRoute = pawnWhite.route(Position.from("b2"), Position.from("b4"));
        assertThat(whitePawnRoute).containsExactly(Position.from("b3"));
    }

    @Test
    @DisplayName("한번 이상 이동한 Pawn 두칸 이동 실패")
    void moveDoubleFail() {
        pawnBlack.moved();
        assertThatThrownBy(() -> pawnBlack.route(Position.from("b6"), Position.from("b4")))
                .isInstanceOf(InvalidMovementException.class);

        pawnWhite.moved();
        assertThatThrownBy(() -> pawnWhite.route(Position.from("b3"), Position.from("b5")))
                .isInstanceOf(InvalidMovementException.class);
    }

    @ParameterizedTest(name = "흑 진영 Pawn 두칸 이동룰에 맞지 않은 이동 실패")
    @MethodSource("moveBlackPawnFailTestcase")
    void moveBlackPawnFail(String from, String to) {
        assertThatThrownBy(() -> pawnBlack.route(Position.from(from), Position.from(to)))
                .isInstanceOf(InvalidMovementException.class);
    }

    private static Stream<Arguments> moveBlackPawnFailTestcase() {
        return Stream.of(
                Arguments.of("b7", "a5"),
                Arguments.of("b7", "c5"),
                Arguments.of("b7", "b4")
        );
    }

    @ParameterizedTest(name = "백 진영 Pawn 두칸 이동룰에 맞지 않은 이동 실패")
    @MethodSource("moveWhitePawnFailTestcase")
    void moveWhitePawnFail(String from, String to) {
        assertThatThrownBy(() -> pawnWhite.route(Position.from(from), Position.from(to)))
                .isInstanceOf(InvalidMovementException.class);
    }

    private static Stream<Arguments> moveWhitePawnFailTestcase() {
        return Stream.of(
                Arguments.of("b2", "a4"),
                Arguments.of("b2", "c4"),
                Arguments.of("b2", "b5")
        );
    }

    @Test
    @DisplayName("폰 대각선으로 움직이는지 확인")
    void diagonal() {
        assertThat(pawnBlack.diagonal(Position.from("b7"), Position.from("c6"))).isTrue();
    }

    @Test
    @DisplayName("폰 전진인지 확인")
    void forward() {
        assertThat(pawnBlack.forward(Position.from("b7"), Position.from("b6"))).isTrue();
    }
}