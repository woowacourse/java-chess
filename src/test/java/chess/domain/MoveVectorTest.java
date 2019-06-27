package chess.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MoveVectorTest {
    @Test
    void MoveVector반환_테스트() {
        assertThat(MoveVector.findVector(new Direction(0, 1))).isEqualTo(MoveVector.NORTH);
    }

    @Test
    void MoveVector가_없을때_예외_테스트() {
        assertThrows(IllegalArgumentException.class, () -> MoveVector.findVector(new Direction(3, 1)));
    }
}
