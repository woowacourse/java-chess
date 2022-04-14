package chess.domain.position;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

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

    @DisplayName("수직 방향에 위치해 있는 다음 position 조회")
    @ParameterizedTest
    @ValueSource(strings = {"a2", "a3", "a4", "a5", "a6", "a7", "a8"})
    void nextInVertical(String toPosition) {
        Position from = Position.from("a1");
        Position to = Position.from(toPosition);

        assertThat(from.next(to)).isEqualTo(Position.from("a2"));
    }

    @DisplayName("수평 방향에 위치해 있는 다음 position 조회")
    @ParameterizedTest
    @ValueSource(strings = {"b1", "c1", "d1", "e1", "f1", "g1", "h1"})
    void nextInHorizontal(String toPosition) {
        Position from = Position.from("a1");
        Position to = Position.from(toPosition);

        assertThat(from.next(to)).isEqualTo(Position.from("b1"));
    }

    @DisplayName("대각선 방향에 위치해 있는 다음 position 조회")
    @ParameterizedTest
    @ValueSource(strings = {"b2", "c3", "d4", "e5", "f6", "g7", "h8"})
    void nextInDiagonal(String toPosition) {
        Position from = Position.from("a1");
        Position to = Position.from(toPosition);

        assertThat(from.next(to)).isEqualTo(Position.from("b2"));
    }
}
