package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.game.Side;
import chess.domain.position.Position;
import chess.exception.InvalidMovementException;
import java.util.Arrays;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class QueenTest {

    private Piece queen;

    @BeforeEach
    void setUp() {
        queen = new Queen(Side.BLACK);
    }

    @ParameterizedTest(name = "Queen 직 이동경로 반환")
    @MethodSource("routeSuccessTestcase")
    void routeSuccess(Position to, Position[] pathPositions) {
        assertThat(queen.route(Position.from("d4"), to))
                .contains(pathPositions);
    }

    private static Stream<Arguments> routeSuccessTestcase() {
        return Stream.of(
                Arguments.of(Position.from("d1"), createPositions("d2", "d3")),
                Arguments.of(Position.from("d8"), createPositions("d5", "d6")),
                Arguments.of(Position.from("a4"), createPositions("b4", "c4")),
                Arguments.of(Position.from("g4"), createPositions("e4", "f4")),
                Arguments.of(Position.from("a1"), createPositions("c3", "b2")),
                Arguments.of(Position.from("a7"), createPositions("c5", "b6")),
                Arguments.of(Position.from("g1"), createPositions("e3", "f2")),
                Arguments.of(Position.from("g7"), createPositions("e5", "f6"))
        );
    }

    private static Position[] createPositions(String... positionNames) {
        return Arrays.stream(positionNames)
                .map(Position::from)
                .toArray(Position[]::new);
    }

    @ParameterizedTest(name = "Queen 이동 실패")
    @MethodSource("routeFailTestcase")
    void routeFail(Position to) {
        assertThatThrownBy(() -> queen.route(Position.from("d4"), to))
                .isInstanceOf(InvalidMovementException.class);
    }

    private static Stream<Arguments> routeFailTestcase() {
        return Stream.of(
                Arguments.of(Position.from("d4")),
                Arguments.of(Position.from("a2")),
                Arguments.of(Position.from("a3")),
                Arguments.of(Position.from("b3")),
                Arguments.of(Position.from("b1")),
                Arguments.of(Position.from("c1")),
                Arguments.of(Position.from("c2"))
        );
    }

}