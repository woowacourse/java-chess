package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.game.Side;
import chess.domain.position.Position;
import chess.exception.InvalidMovementException;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

class PawnTest {

    private static final Piece BLANK = Blank.getBlank();
    private static final Piece BLACK_OPPONENT_PIECE = new Pawn(Side.BLACK);
    private static final Piece WHITE_OPPONENT_PIECE = new Pawn(Side.WHITE);

    private static Piece pawnBlack;
    private static Piece pawnWhite;

    private final Position c7 = Position.from("c7");
    private final Position c2 = Position.from("c2");

    @BeforeEach
    void setUp() {
        pawnBlack = new Pawn(Side.BLACK);
        pawnWhite = new Pawn(Side.WHITE);
    }

    @ParameterizedTest(name = "Black Pawn 대각 이동 성공")
    @MethodSource("routeBlackPawnSuccessTestcase")
    void routeBlackPawnSuccess(Position to) {
        assertThat(pawnBlack.route(c7, to, WHITE_OPPONENT_PIECE, Side.BLACK)).isEmpty();
    }

    private static Stream<Arguments> routeBlackPawnSuccessTestcase() {
        return Stream.of(
                Arguments.of(Position.from("b6")),
                Arguments.of(Position.from("d6"))
        );
    }

    @ParameterizedTest(name = "White Pawn 대각 이동 성공")
    @MethodSource("routeWhitePawnSuccessTestcase")
    void routeWhitePawnSuccess(Position to) {
        assertThat(pawnWhite.route(c2, to, BLACK_OPPONENT_PIECE, Side.WHITE)).isEmpty();
    }

    private static Stream<Arguments> routeWhitePawnSuccessTestcase() {
        return Stream.of(
                Arguments.of(Position.from("b3")),
                Arguments.of(Position.from("d3"))
        );
    }

    @ParameterizedTest(name = "Black Pawn 이동 실패")
    @ValueSource(strings = {"a7", "a6", "b7", "b6", "c4", "d7", "d6", "e7", "e6"})
    void routeBlackPawnFail(String to) {
        assertThatThrownBy(() -> pawnBlack.route(c7, Position.from(to), BLANK, Side.BLACK))
                .isInstanceOf(InvalidMovementException.class);
    }

    @ParameterizedTest(name = "White Pawn 이동 실패")
    @ValueSource(strings = {"a2", "a3", "b2", "b3", "c5", "d2", "d3", "e2", "e3"})
    void routeWhitePawnFail(Position to) {
        assertThatThrownBy(() -> pawnWhite.route(c2, to, BLANK, Side.WHITE))
                .isInstanceOf(InvalidMovementException.class);
    }

    @Test
    @DisplayName("한번도 움직이지 않은 Pawn 두칸 이동, 경로 반환")
    void moveDoubleSuccess() {
        List<Position> blackPawnRoute = pawnBlack.route(c7, Position.from("c5"), BLANK, Side.BLACK);
        assertThat(blackPawnRoute).containsExactly(Position.from("c6"));

        List<Position> whitePawnRoute = pawnWhite.route(c2, Position.from("c4"), BLANK, Side.WHITE);
        assertThat(whitePawnRoute).containsExactly(Position.from("c3"));
    }

    @Test
    @DisplayName("한번 이상 이동한 Pawn 두칸 이동 실패")
    void moveDoubleFail() {
        pawnBlack.moved();
        assertThatThrownBy(
                () -> pawnBlack.route(Position.from("c6"), Position.from("c4"), BLANK, Side.BLACK))
                .isInstanceOf(InvalidMovementException.class);

        pawnWhite.moved();
        assertThatThrownBy(
                () -> pawnWhite.route(Position.from("c3"), Position.from("c5"), BLANK, Side.WHITE))
                .isInstanceOf(InvalidMovementException.class);
    }

    @ParameterizedTest(name = "Black Pawn 두칸 이동룰에 맞지 않은 이동 실패")
    @MethodSource("moveBlackPawnFailTestcase")
    void moveBlackPawnFail(String from, String to) {
        assertThatThrownBy(
                () -> pawnBlack.route(Position.from(from), Position.from(to), BLANK, Side.BLACK))
                .isInstanceOf(InvalidMovementException.class);
    }

    private static Stream<Arguments> moveBlackPawnFailTestcase() {
        return Stream.of(
                Arguments.of("c7", "b5"),
                Arguments.of("c7", "d5"),
                Arguments.of("c7", "c4")
        );
    }

    @ParameterizedTest(name = "White Pawn 두칸 이동룰에 맞지 않은 이동 실패")
    @ValueSource(strings = {"b4", "d4", "c5"})
    void moveWhitePawnFail(String to) {
        assertThatThrownBy(() -> pawnWhite.route(c2, Position.from(to), BLANK, Side.WHITE))
                .isInstanceOf(InvalidMovementException.class);
    }

//    @ParameterizedTest(name = "Black Pawn 전진 이동 검사")
//    @CsvSource(value = {"c3", "c4"})
//    void forwardBlackPawn(String to) {
//        assertThat(pawnBlack.forward(c2, Position.from(to))).isTrue();
//    }
//
//    @ParameterizedTest(name = "White Pawn 전진 이동 검사")
//    @CsvSource(value = {"c6", "c5"})
//    void forwardWhitePawn(String to) {
//        assertThat(pawnWhite.forward(c7, Position.from(to))).isTrue();
//    }
//
//    @ParameterizedTest(name = "Black Pawn 전진 이동 실패")
//    @CsvSource(value = {"b2", "b3", "b4", "d2", "d3", "d4", "c5"})
//    void forwardBlackPawnFail(String to) {
//        assertThat(pawnBlack.forward(c2, Position.from(to))).isFalse();
//    }
//
//    @ParameterizedTest(name = "White Pawn 전진 이동 실패")
//    @CsvSource(value = {"b7", "b6", "b5", "d7", "d6", "d5", "c4"})
//    void forwardWhitePawnFail(String to) {
//        assertThat(pawnWhite.forward(c7, Position.from(to))).isFalse();
//    }
}