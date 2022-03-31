package chess.domain.position;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class PositionTest {

    @Test
    @DisplayName("체스판의 x 범위를 넘어서는 위치를 입력할 경우 예외 발생")
    void fromXOverException() {
        assertThatThrownBy(() -> Position.from("z1"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("체스판 범위를 벗어납니다.");
    }

    @Test
    @DisplayName("체스판의 y 범위를 넘어서는 위치를 입력할 경우 예외 발생")
    void fromYOverException() {
        assertThatThrownBy(() -> Position.from("a9"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("체스판 범위를 벗어납니다.");
    }

    @Test
    @DisplayName("수직 방향에 위치해 있는 다음 position 조회")
    void nextInVertical() {
        Position from = Position.from("a1");
        Position to = Position.from("a8");

        assertThat(from.next(to)).isEqualTo(Position.from("a2"));
    }

    @Test
    @DisplayName("수평 방향에 위치해 있는 다음 position 조회")
    void nextInHorizontal() {
        Position from = Position.from("a1");
        Position to = Position.from("h1");

        assertThat(from.next(to)).isEqualTo(Position.from("b1"));
    }

    @Test
    @DisplayName("대각선 방향에 위치해 있는 다음 position 조회")
    void nextInDiagonal() {
        Position from = Position.from("a1");
        Position to = Position.from("h8");

        assertThat(from.next(to)).isEqualTo(Position.from("b2"));
    }
}
