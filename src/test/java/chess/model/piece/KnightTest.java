package chess.model.piece;

import static org.assertj.core.api.Assertions.assertThat;

import chess.model.Color;
import chess.model.board.Square;
import chess.model.strategy.move.MoveType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class KnightTest {
    private Knight knight;
    private Square source;

    @BeforeEach
    void setUp() {
        knight = new Knight(Color.BLACK);
        source = Square.of("d4");
    }

    @ParameterizedTest
    @ValueSource(strings = {"f5", "f3", "b5", "b3", "e6", "e2", "c6", "c2"})
    void movable(String target) {
        assertThat(knight.movable(source, Square.of(target), MoveType.MOVE))
                .isTrue();
    }

    @ParameterizedTest
    @ValueSource(strings = {"d6", "a4", "f6", "a1"})
    void cannotMovable(String target) {
        assertThat(knight.movable(source, Square.of(target), MoveType.MOVE))
                .isFalse();
    }
}
