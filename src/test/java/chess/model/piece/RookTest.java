package chess.model.piece;

import static org.assertj.core.api.Assertions.assertThat;

import chess.model.Color;
import chess.model.board.Square;
import chess.model.strategy.move.MoveType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class RookTest {
    private Rook rook;
    private Square source;

    @BeforeEach
    void setUp() {
        rook = new Rook(Color.BLACK);
        source = Square.of("d4");
    }

    @ParameterizedTest
    @ValueSource(strings = {"d1", "g4", "a4", "d8"})
    void movable(String target) {
        assertThat(rook.movable(source, Square.of(target), MoveType.MOVE))
                .isTrue();
    }

    @ParameterizedTest
    @ValueSource(strings = {"a1", "a8", "h8", "h1"})
    void cannotMovable(String target) {
        assertThat(rook.movable(source, Square.of(target), MoveType.MOVE))
                .isFalse();
    }
}
