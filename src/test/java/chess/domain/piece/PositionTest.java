package chess.domain.piece;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class PositionTest {

    @Test
    @DisplayName("이동 거리와 방향을 주었을 때 목표 지점의 위치를 반환한다.")
    void calculate() {
        // given
        Position position = new Position(0, 0);

        // when
        Position targetPosition = position.calculate(3, -3);

        // then
        assertThat(targetPosition)
                .isEqualTo(new Position(3, -3));
    }
}
