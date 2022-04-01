package chess.model.piece;

import static org.assertj.core.api.Assertions.assertThat;

import chess.model.Color;
import chess.model.board.Square;
import chess.model.strategy.move.MoveType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class KingTest {
    private King king;
    private Square source;

    @BeforeEach
    void setUp() {
        king = new King(Color.BLACK);
        source = Square.of("e4");
    }

    @ParameterizedTest
    @ValueSource(strings = {"f5", "f3", "d3", "d5", "f4", "d4", "e5", "e3"})
    void movable(String targetSquareName) {
        assertThat(king.movable(source, Square.of(targetSquareName), MoveType.MOVE))
                .isTrue();
    }

    @ParameterizedTest
    @ValueSource(strings = {"d6", "a4", "f6", "a1"})
    void cannotMovable(String targetSquareName) {
        assertThat(king.movable(source, Square.of(targetSquareName), MoveType.MOVE))
                .isFalse();
    }
}
