package chess;

import chess.model.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PositionTest {

    @Test
    @DisplayName("체스판을 벗어나는 position이 주어지면 예외가 발생한다.")
    void outOfRangePositionTest() {
        assertThatThrownBy(() -> Position.from("a9"))
                .isExactlyInstanceOf(IllegalArgumentException.class);
    }
}
