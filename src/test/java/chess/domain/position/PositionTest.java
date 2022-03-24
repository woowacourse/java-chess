package chess.domain.position;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class PositionTest {

    @Test
    @DisplayName("체스판의 file 범위를 넘어서는 위치를 입력할 경우 예외 발생")
    void boardFileRangeOverException() {
        assertThatThrownBy(() -> Position.from("z1"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("체스판 범위를 벗어납니다.");
    }

    @Test
    @DisplayName("체스판의 rank 범위를 넘어서는 위치를 입력할 경우 예외 발생")
    void boardRankRangeOverException() {
        assertThatThrownBy(() -> Position.from("a9"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("체스판 범위를 벗어납니다.");
    }
}
