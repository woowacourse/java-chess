import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PositionTest {

    @DisplayName("source position과 target position간 거리차이를 반환한다.")
    @Test
    void shouldReturnIncrementBetweenSourceAndTargetWhenInputTargetToSource() {
        Position sourcePosition = Position.of("a", "1");
        Position targetPosition = Position.of("d", "6");

        Increment increment = sourcePosition.calculateIncrement(targetPosition);
        Assertions.assertThat(increment).isEqualTo(new Increment(3, 5));
    }

}