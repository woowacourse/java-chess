package chess.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BoardGeneratorTest {

    @Test
    void generateTest() {
        assertEquals(64, BoardGenerator.generate().size());
    }
}