package chess.domain.position;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class PositionTest {
    @DisplayName("Position 객체 생성 확인")
    @Test
    void 위치_객체_생성() {
        Position position = Position.of("a1");

        assertThat(position.getX()).isEqualTo('a');
        assertThat(position.getY()).isEqualTo('1');
    }

    @DisplayName("체스판에 있는 범위 Position이 아닐 경우")
    @Test
    void 위치_객체_생성_예외_테스트() {
        assertThatThrownBy(() -> Position.of("z9"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("두 위치의 Y 값의 차이를 구한다.")
    @Test
    void Y_값_차이_구하기() {
        Position position1 = Position.of("a1");
        Position position2 = Position.of("a5");

        int difference = position1.yDistance(position2);

        assertThat(difference).isEqualTo(-4);
    }

    @DisplayName("두 위치의 Y 값의 차이가 양수인지 확인한다.")
    @Test
    void Y_값_차이가_양수인지_확인하기() {
        Position position1 = Position.of("a1");
        Position position2 = Position.of("a5");

        boolean result = position1.largeY(position2);

        assertThat(result).isFalse();
    }

    @DisplayName("두 위치의 X 값의 차이가 양수인지 확인한다.")
    @Test
    void X_값_차이가_양수인지_확인하기() {
        Position position1 = Position.of("a1");
        Position position2 = Position.of("c5");

        boolean result = position1.largeX(position2);

        assertThat(result).isFalse();
    }

    @DisplayName("두 x, y의 차가 같은지 확인한다.")
    @Test
    void X_값과_Y_값의_차이가_같다() {
        Position position1 = Position.of("a1");
        Position position2 = Position.of("h8");

        boolean result = position1.isDiagonal(position2);

        assertThat(result).isTrue();
    }

    @DisplayName("두 위치가 x 또는 y값이 같은지 확인한다.")
    @Test
    void X_값_또는_Y_값이_같다() {
        Position position1 = Position.of("a1");
        Position position2 = Position.of("a8");

        boolean result = position1.isCross(position2);

        assertThat(result).isTrue();
    }
}
