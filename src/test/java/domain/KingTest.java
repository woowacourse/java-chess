package domain;

import chess.domain.King;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class KingTest {

    @Test
    void 정상_생성_Test() {
        assertDoesNotThrow(() -> new King(0));
    }

    @Test
    void King_정상_이동_Test() {

    }
}
