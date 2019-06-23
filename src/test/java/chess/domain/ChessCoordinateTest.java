package chess.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ChessCoordinateTest {

    @Test
    void invalidSymbol() {
        assertThrows(IllegalArgumentException.class, () -> ChessCoordinate.valueOf("z1"));
    }
}
