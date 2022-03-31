package chess.domain.position;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class CoordinateYTest {

    @Test
    @DisplayName("체스판의 범위를 벗어나는 순서를 입력할 경우 예외 발생")
    void fromOrderException() {
        assertThatThrownBy(() -> CoordinateY.from(10))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("체스판 범위를 벗어납니다.");
    }

    @Test
    @DisplayName("다음 y 값 조회")
    void next() {
        CoordinateY from = CoordinateY.from(1);
        CoordinateY to = CoordinateY.from(3);

        assertThat(from.next(to)).isEqualTo(CoordinateY.TWO);
    }

    @Test
    @DisplayName("시작 y 와 도착 y 가 같을 경우 도착 y 조회")
    void nextEqualsPosition() {
        CoordinateY from = CoordinateY.from(8);
        CoordinateY to = CoordinateY.from(8);

        assertThat(from.next(to)).isEqualTo(CoordinateY.EIGHT);
    }
}
