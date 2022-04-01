package chess.model.piece;

import static org.assertj.core.api.Assertions.assertThat;

import chess.model.Color;
import chess.model.board.Square;
import chess.model.strategy.move.MoveType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class QueenTest {
    private Queen queen;
    private Square source;

    @BeforeEach
    void setUp() {
        queen = new Queen(Color.BLACK);
        source = Square.of("d4");
    }

    @ParameterizedTest
    @ValueSource(strings = {"d8", "d1", "a4", "h4", "a1", "a7", "h8", "g1"})
    void movable(String target) {
        assertThat(queen.movable(source, Square.of(target), MoveType.MOVE))
                .isTrue();
    }

    @ParameterizedTest
    @ValueSource(strings = {"h2", "c6"})
    void cannotMovable(String target) {
        assertThat(queen.movable(source, Square.of(target), MoveType.MOVE))
                .isFalse();
    }
}
