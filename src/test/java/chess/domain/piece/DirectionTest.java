package chess.domain.piece;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class DirectionTest {

    private final Direction direction = Direction.NORTH;

    @Test
    @DisplayName("올바른 방향 및 이동 위치 테스트")
    void isCorrectDirection() {

        // when
        final boolean correctDirection = direction.isCorrectDirection(0, 1, 1);

        // then
        assertThat(correctDirection).isTrue();
    }

    @Test
    @DisplayName("방향과 이동 위치의 방향이 다른 경우 테스트")
    void isCorrectDirection_Opposite_False() {

        // when
        final boolean correctDirection = direction.isCorrectDirection(1, 0, 1);

        // then
        assertThat(correctDirection).isFalse();
    }

    @Test
    @DisplayName("방향과 이동 위치의 방향이 같지만 움직일 수 있는 거리가 짧을 경우 테스트")
    void isCorrectDirection_TooShortMovableLength_False() {

        // when
        final boolean correctDirection = direction.isCorrectDirection(0, 2, 1);

        // then
        assertThat(correctDirection).isFalse();
    }
}
