package domain.piece.info;

import static org.junit.jupiter.api.Assertions.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class VectorTest {

    @Test
    @DisplayName("두 위치가 같으면 예외가 발생한다")
    void isSamePosition() {
        final Position position = new Position(File.A, Rank.ONE);
        Assertions.assertThatThrownBy(() -> new Vector(position, position))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("두 위치가 같습니다");
    }

    @Test
    void isDiagonal() {
    }
}
