package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.game.Side;
import chess.domain.position.Position;
import chess.exception.InvalidMovementException;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class KnightTest {

    private Piece knight;

    @BeforeEach
    void setUp() {
        knight = new Knight(Side.BLACK);
    }

    @ParameterizedTest(name = "Knight 빈 이동경로 반환")
    @MethodSource("routeSuccessTestcase")
    void routeSuccess(Position to) {
        assertThat(knight.route(Position.from("c3"), to)).isEmpty();
    }

    private static Stream<Arguments> routeSuccessTestcase() {
        return Stream.of(
                Arguments.of(Position.from("b1")),
                Arguments.of(Position.from("a2")),
                Arguments.of(Position.from("a4")),
                Arguments.of(Position.from("b5")),
                Arguments.of(Position.from("e4")),
                Arguments.of(Position.from("e2")),
                Arguments.of(Position.from("d1"))
        );
    }

    @ParameterizedTest(name = "Knight 이동 실패")
    @MethodSource("routeFailTestcase")
    void routeFail(Position to) {
        assertThatThrownBy(() -> knight.route(Position.from("a1"), to))
                .isInstanceOf(InvalidMovementException.class);
    }

    private static Stream<Arguments> routeFailTestcase() {
        return Stream.of(
                Arguments.of(Position.from("a1")),
                Arguments.of(Position.from("a2")),
                Arguments.of(Position.from("a3")),
                Arguments.of(Position.from("b1")),
                Arguments.of(Position.from("b2")),
                Arguments.of(Position.from("c1")),
                Arguments.of(Position.from("c3"))
        );
    }
}