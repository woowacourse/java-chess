package domain.square;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class EmptySquareTest {

    @Test
    @DisplayName("빈 칸은 기본 상태를 가진다")
    void propertyTest() {
        Square square = new EmptySquare();

        assertThat(square.canReap()).isFalse();
        assertThat(square.isFirstMove()).isTrue();
        assertThat(square.getCamp()).isNull();
        assertThat(square.getPieceType()).isNull();
    }
}
