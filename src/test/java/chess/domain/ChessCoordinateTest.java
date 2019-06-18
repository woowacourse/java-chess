package chess.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ChessCoordinateTest {

    @Test
    void invalidSymbol() {
        assertThat(ChessCoordinate.valueOf("z1").isPresent()).isFalse();
    }
}
