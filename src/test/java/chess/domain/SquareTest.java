package chess.domain;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.Test;

class SquareTest {

    @Test
    void createSquare() {
        assertDoesNotThrow(() -> new Square(File.A, Rank.ONE));
    }
}
