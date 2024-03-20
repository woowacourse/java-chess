package chess.domain.movement;

import static org.assertj.core.api.Assertions.assertThatCode;

import chess.domain.Color;
import chess.domain.movement.direction.DownDirection;
import chess.domain.movement.policy.ColorPolicy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MovementTest {

    @Test
    @DisplayName("이동은 이동 방향과 정책을 가진다.")
    void createMovement() {
        assertThatCode(() -> new Movement(new ColorPolicy(Color.WHITE), new DownDirection(3)));
    }
}
