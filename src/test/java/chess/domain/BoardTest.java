package chess.domain;

import chess.domain.position.Position;
import chess.exception.InvalidMovementException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class BoardTest {
    Board board;

    @BeforeEach
    void setUp() {
        board = Board.getGamingBoard();
    }

    @ParameterizedTest
    @MethodSource("moveSuccessTestcase")
    void moveSuccess(Position from, Position to) {
        board.move(from, to, Side.WHITE);
    }

    private static Stream<Arguments> moveSuccessTestcase() {
        return Stream.of(
                Arguments.of(Position.of("b2"), Position.of("b3")),
                Arguments.of(Position.of("b2"), Position.of("b4")),
                Arguments.of(Position.of("b1"), Position.of("c3"))
        );
    }

    @ParameterizedTest
    @MethodSource("moveFailTestcase")
    void moveFail(Position from, Position to) {
        assertThatThrownBy(() -> board.move(from, to, Side.WHITE))
                .isInstanceOf(InvalidMovementException.class);
    }

    private static Stream<Arguments> moveFailTestcase() {
        return Stream.of(
                Arguments.of(Position.of("a1"), Position.of("a3")),
                Arguments.of(Position.of("a1"), Position.of("a2")),
                Arguments.of(Position.of("c1"), Position.of("b2")),
                Arguments.of(Position.of("c1"), Position.of("a3"))
        );
    }
}