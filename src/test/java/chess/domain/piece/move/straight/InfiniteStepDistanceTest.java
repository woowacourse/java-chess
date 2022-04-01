package chess.domain.piece.move.straight;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class InfiniteStepDistanceTest {
    @Test
    @DisplayName("거리를 만든다.")
    public void createDistance() {
        // given & when
        Distance distance = InfiniteStepDistance.init();
        // then
        assertThat(distance).isNotNull();
    }

    @Test
    @DisplayName("거리를 한 칸 줄인다")
    public void decreaseDistance() {
        // given
        Distance distance = InfiniteStepDistance.init();
        // when
        Distance decreased = distance.decreaseOne();
        // then
        assertThat(decreased).isInstanceOf(InfiniteStepDistance.class);
    }

    @Test
    @DisplayName("거리가 남았는지 확인한다.")
    public void checkIfDistanceLeft() {
        // given
        Distance distance = InfiniteStepDistance.init();
        Distance decreased = distance.decreaseOne();
        // when
        boolean isLeft = decreased.isLeft();
        // then
        assertThat(isLeft).isTrue();
    }
}