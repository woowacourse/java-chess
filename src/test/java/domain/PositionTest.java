package domain;

import chess.domain.Position;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class PositionTest {

    @Test
    void 입력받은_Positon이_유효한_경우() {
        assertTrue(Position.valueOf("a4") != null);
    }

    @Test
    void 입력받은_Positon이_유효하지_않은_경우() {
        assertTrue(Position.valueOf("k1") == null);
    }
}
