package chess.domain.piece;

import chess.domain.Side;
import chess.domain.position.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class KingTest {

    King king;

    @BeforeEach
    void setUp() {
        king = new King(Side.BLACK);
    }

    @ParameterizedTest
    @CsvSource({"b2,a1", "b2,b3", "b2,a2"})
    void routeSuccess(String from, String to) {
        assertThat(king.route(Position.of(from), Position.of(to))).isEmpty();
    }

    @ParameterizedTest
    @MethodSource("routeFailTestcase")
    void routeFail(Position from, Position to) {
        assertThatThrownBy(() -> king.route(from, to))
                .isInstanceOf(IllegalArgumentException.class);
    }

    private static Stream<Arguments> routeFailTestcase() {
        return Stream.of(
                Arguments.of(Position.of("a1"), Position.of("a1")),
                Arguments.of(Position.of("a1"), Position.of("a3"))
        );
    }
}