package chess.domain.piece;

import chess.domain.Side;
import chess.domain.position.Position;
import chess.exception.InvalidMovementException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class KingTest {

    private Piece king;

    @BeforeEach
    void setUp() {
        king = new King(Side.BLACK);
    }

    @ParameterizedTest(name = "King 빈 이동경로 반환")
    @CsvSource({"b2,a1", "b2,b3", "b2,a2"})
    void routeSuccess(String from, String to) {
        assertThat(king.route(Position.from(from), Position.from(to))).isEmpty();
    }

    @ParameterizedTest(name = "King 이동 실패")
    @MethodSource("routeFailTestcase")
    void routeFail(Position from, Position to) {
        assertThatThrownBy(() -> king.route(from, to))
                .isInstanceOf(InvalidMovementException.class);
    }

    private static Stream<Arguments> routeFailTestcase() {
        return Stream.of(
                Arguments.of(Position.from("a1"), Position.from("a1")),
                Arguments.of(Position.from("a1"), Position.from("a3"))
        );
    }
}