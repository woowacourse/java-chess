package chess.domain.position;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class DirectionTest {
    @DisplayName("존재하지 않는 방향일 때 예외")
    @Test
    void 존재하지_않는_방향() {
        Position source = Position.of("a1");
        Position target = Position.of("b4");
        assertThatThrownBy(() ->
                Direction.findDirectionByTwoPosition(source, target))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
