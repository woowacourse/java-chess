package domain;

import chess.domain.PointFactory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class PointTest {

    @Test
    void 올바른_생성(){
        assertDoesNotThrow(()-> PointFactory.of("b1"));
    }
}
