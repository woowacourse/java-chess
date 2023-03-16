package chess.domain;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import chess.domain.square.File;
import chess.domain.square.Rank;
import chess.domain.square.Square;
import org.junit.jupiter.api.Test;

class SquareTest {

    @Test
    void createSquare() {
        assertDoesNotThrow(() -> Square.of(File.A, Rank.ONE));
    }
}
