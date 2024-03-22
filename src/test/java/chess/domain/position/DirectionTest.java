package chess.domain.position;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class DirectionTest {

    @DisplayName("목적지가 더 아래에 있다면 움직이는 방향은 DOWN이다")
    @Test
    void should_DirectionIsDown_When_DestinationIsLowerThanStart() {
        assertThat(Direction.from(false)).isEqualTo(Direction.DOWN);
    }

    @DisplayName("목적지가 더 위에 있다면 움직이는 방향은 UP이다")
    @Test
    void should_DirectionIsUp_When_DestinationIsHigherThanStart() {
        assertThat(Direction.from(true)).isEqualTo(Direction.UP);
    }

}
