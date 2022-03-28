package chess.domain.piece.move.straight;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class OneStepDistanceTest {

    @Test
    @DisplayName("거리를 만든다.")
    public void createDistance() {
        // given & when
        Distance distance = OneStepDistance.init();
        // then
        assertThat(distance).isNotNull();
    }

    @Test
    @DisplayName("거리를 한 칸 줄인다")
    public void decreaseDistance() {
        // given
        Distance distance = OneStepDistance.init();
        // when
        Distance decreased = distance.decreaseOne();
        // then
        assertThat(decreased).isInstanceOf(OneStepDistance.class);
    }

    @Test
    @DisplayName("거리가 남았는지 확인한다.")
    public void checkIfDistanceLeft() {
        // given
        Distance distance = OneStepDistance.init();
        Distance decreased = distance.decreaseOne();
        // when
        boolean isLeft = decreased.isLeft();
        // then
        assertThat(isLeft).isFalse();
    }
}