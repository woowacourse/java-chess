package chess.domain;

import chess.domain.Position;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class PositionTest {

    @Test
    void 입력받은_Positon이_유효한_경우() {
        assertTrue(Position.valueOf("a4") != null);
    }

    @Test
    void 입력받은_Positon이_유효하지_않은_경우() {
        assertTrue(Position.valueOf("k1") == null);
    }

    @Test
    void 입력받은_Positon이_유효한_경우_예외_반환_안함() {
        assertDoesNotThrow(() -> Position.valueOf("e7"));
    }

    @Test
    void 입력받은_Positon이_유효하지_않은_경우_예외_반환() {
        assertThrows(IllegalArgumentException.class, () -> Position.valueOf("k1"));
    }

    @Test
    void 이동된_포지션_반환_확인() {
        assertThat(Position.valueOf("a1").movePosition(2, 3)).isEqualTo(Position.valueOf("d3"));
    }


}
