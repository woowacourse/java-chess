package chess.domain.piece;

import chess.domain.Side;
import chess.domain.position.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class KnightTest {

    private Piece knight;

    @BeforeEach
    void setUp() {
        knight = new Knight(Side.BLACK);
    }

    @ParameterizedTest
    @MethodSource("routeSuccessTestcase")
    void routeSuccess(Position to) {
        assertThat(knight.route(Position.of("c3"), to)).isEmpty();
    }

    private static Stream<Arguments> routeSuccessTestcase() {
        return Stream.of(
                Arguments.of(Position.of("b1")),
                Arguments.of(Position.of("a2")),
                Arguments.of(Position.of("a4")),
                Arguments.of(Position.of("b5")),
                Arguments.of(Position.of("e4")),
                Arguments.of(Position.of("e2")),
                Arguments.of(Position.of("d1"))
        );
    }

    @ParameterizedTest
    @MethodSource("routeFailTestcase")
    void routeFail(Position to) {
        assertThatThrownBy(() -> knight.route(Position.of("a1"), to))
                .isInstanceOf(IllegalArgumentException.class);
    }

    private static Stream<Arguments> routeFailTestcase() {
        return Stream.of(
                Arguments.of(Position.of("a1")),
                Arguments.of(Position.of("a2")),
                Arguments.of(Position.of("a3")),
                Arguments.of(Position.of("b1")),
                Arguments.of(Position.of("b2")),
                Arguments.of(Position.of("c1")),
                Arguments.of(Position.of("c3"))
        );
    }
}