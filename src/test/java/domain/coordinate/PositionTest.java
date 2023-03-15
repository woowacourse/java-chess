package domain.coordinate;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class PositionTest {

    @Test
    @DisplayName("Position 캐싱을 확인한다.")
    void givenCoordinate_thenReturnCachingPosition() {
        Position position = Position.of(0, 0);

        assertThat(position == Position.of(0, 0)).isTrue();
    }
}
