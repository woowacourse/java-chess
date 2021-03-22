package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.Side;
import chess.domain.position.Position;
import chess.exception.InvalidMovementException;
import java.util.Arrays;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class RookTest {

    private Piece rook;

    @BeforeEach
    void setUp() {
        rook = new Rook(Side.BLACK);
    }

    @ParameterizedTest(name = "Rook 수직수평 이동경로 반환")
    @MethodSource("routeSuccessTestcase")
    void routeSuccess(Position to, Position[] pathPositions) {
        assertThat(rook.route(Position.from("d4"), to))
                .contains(pathPositions);
    }

    private static Stream<Arguments> routeSuccessTestcase() {
        return Stream.of(
                Arguments.of(Position.from("d1"), createPositions("d2", "d3")),
                Arguments.of(Position.from("d8"), createPositions("d5", "d6")),
                Arguments.of(Position.from("a4"), createPositions("b4", "c4")),
                Arguments.of(Position.from("g4"), createPositions("e4", "f4"))
        );
    }

    private static Position[] createPositions(String... positionNames) {
        return Arrays.stream(positionNames)
                .map(Position::from)
                .toArray(Position[]::new);
    }

    @ParameterizedTest(name = "Rook 이동 실패")
    @MethodSource("routeFailTestcase")
    void routeFail(Position to) {
        assertThatThrownBy(() -> rook.route(Position.from("d4"), to))
                .isInstanceOf(InvalidMovementException.class);
    }

    private static Stream<Arguments> routeFailTestcase() {
        return Stream.of(
                Arguments.of(Position.from("d4")),
                Arguments.of(Position.from("c3")),
                Arguments.of(Position.from("e3")),
                Arguments.of(Position.from("c5")),
                Arguments.of(Position.from("e5")),
                Arguments.of(Position.from("e6"))
        );
    }
}