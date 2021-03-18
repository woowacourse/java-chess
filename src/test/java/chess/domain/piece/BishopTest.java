package chess.domain.piece;

import chess.domain.Side;
import chess.domain.position.Position;
import chess.exception.InvalidMovementException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class BishopTest {

    private Piece bishop;

    @BeforeEach
    void setUp() {
        bishop = new Bishop(Side.BLACK);
    }

    @ParameterizedTest(name = "Bishop 대각 이동경로 반환")
    @MethodSource("routeSuccessTestcase")
    void routeSuccess(Position to, Position[] pathPositions) {
        assertThat(bishop.route(Position.of("d4"), to))
                .contains(pathPositions);
    }

    private static Stream<Arguments> routeSuccessTestcase() {
        return Stream.of(
                Arguments.of(Position.of("a1"), createPositions("c3", "b2")),
                Arguments.of(Position.of("a7"), createPositions("c5", "b6")),
                Arguments.of(Position.of("g1"), createPositions("e3", "f2")),
                Arguments.of(Position.of("g7"), createPositions("e5", "f6"))
        );
    }

    private static Position[] createPositions(String... positionNames) {
        return Arrays.stream(positionNames)
                .map(Position::of)
                .toArray(Position[]::new);
    }

    @ParameterizedTest(name = "Bishop 이동 실패")
    @MethodSource("routeFailTestcase")
    void routeFail(Position to) {
        assertThatThrownBy(() -> bishop.route(Position.of("d4"), to))
                .isInstanceOf(InvalidMovementException.class);
    }

    private static Stream<Arguments> routeFailTestcase() {
        return Stream.of(
                Arguments.of(Position.of("d4")),
                Arguments.of(Position.of("d3")),
                Arguments.of(Position.of("d5")),
                Arguments.of(Position.of("c4")),
                Arguments.of(Position.of("e4")),
                Arguments.of(Position.of("e6"))
        );
    }
}