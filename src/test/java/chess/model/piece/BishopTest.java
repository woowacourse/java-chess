package chess.model.piece;

import static org.assertj.core.api.Assertions.assertThat;

import chess.model.Color;
import chess.model.File;
import chess.model.Rank;
import chess.model.board.Square;
import chess.model.strategy.move.MoveType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class BishopTest {

    private Bishop bishop;

    @BeforeEach
    void setUp() {
        bishop = new Bishop(Color.BLACK);
    }

    @ParameterizedTest
    @ValueSource(strings = {"c5", "f6", "b2", "g1"})
    void checkBishopMovable(String targetSquareName) {
        Square source = Square.of(File.D, Rank.FOUR);
        assertThat(bishop.movable(source, Square.of(targetSquareName), MoveType.MOVE))
                .isTrue();
    }

    @Test
    void cannotMovable() {
        Square source = Square.of(File.D, Rank.FOUR);
        assertThat(bishop.movable(source, Square.of("c6"), MoveType.MOVE))
                .isFalse();
    }
}
