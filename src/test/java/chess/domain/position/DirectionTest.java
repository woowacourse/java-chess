package chess.domain.position;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DirectionTest {

    @DisplayName("source와 target 위치에 따른 방향 정보를 반환한다.")
    @Test
    void source와_target_위치에_따른_방향_정보를_반환한다() {
        Position source = new Position("c4");
        Position target = new Position("e4");

        assertThat(Direction.of(source, target)).isEqualTo(Direction.RIGHT);
    }
}
