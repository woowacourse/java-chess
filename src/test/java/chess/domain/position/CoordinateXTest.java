package chess.domain.position;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class CoordinateXTest {

    @Test
    @DisplayName("체스판의 범위를 벗어나는 순서를 입력할 경우 예외 발생")
    void fromOrderException() {
        assertThatThrownBy(() -> CoordinateX.from(10))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("체스판 범위를 벗어납니다.");
    }

    @Test
    @DisplayName("체스판의 범위를 벗어나는 값을 입력할 경우 예외 발생")
    void fromNameException() {
        assertThatThrownBy(() -> CoordinateX.from("i"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("체스판 범위를 벗어납니다.");
    }

    @Test
    @DisplayName("다음 x 값 조회")
    void next() {
        CoordinateX from = CoordinateX.from("a");
        CoordinateX to = CoordinateX.from("c");

        assertThat(from.next(to)).isEqualTo(CoordinateX.B);
    }

    @Test
    @DisplayName("시작 x 와 도착 x 가 같을 경우 도착 x 조회")
    void nextEqualsPosition() {
        CoordinateX from = CoordinateX.from("h");
        CoordinateX to = CoordinateX.from("h");

        assertThat(from.next(to)).isEqualTo(CoordinateX.H);
    }
}
