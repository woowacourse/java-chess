package techcourse.fp.chess.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DirectionTest {

    @Test
    @DisplayName("여러번 이동하여 닿을 수 있는 위치면 true를 반환한다.")
    void is_reachable_true() {
        //given
        final Direction direction = Direction.DOWN;
        //when
        final boolean actual = direction.isReachable(0, -5);
        //then
        assertThat(actual).isTrue();
    }

    @Test
    @DisplayName("여러번 이동하여도 닿을 수 없으면 false를 반환한다.")
    void is_reachable_false() {
        //given
        final Direction direction = Direction.DOWN;
        //when
        final boolean actual = direction.isReachable(3, -5);
        //then
        assertThat(actual).isFalse();
    }
}
