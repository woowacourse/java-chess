package chess.domain.movement;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.junit.jupiter.api.Assertions.assertAll;

import chess.domain.movement.direction.DownDirection;
import chess.domain.movement.direction.UpRightDirection;
import chess.domain.movement.policy.ColorPolicy;
import chess.domain.piece.Color;
import chess.domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MovementTest {

    @Test
    @DisplayName("이동은 이동 방향과 정책을 가진다.")
    void createMovement() {
        assertThatCode(() -> new Movement(new ColorPolicy(Color.WHITE), new DownDirection(3)));
    }

    @Test
    @DisplayName("이동은 가지고 있는 정책 조건이 만족하는지 확인한다.")
    void isSatisfied() {
        Movement movement = new Movement(new ColorPolicy(Color.WHITE), new UpRightDirection(2));
        assertAll(
                () -> assertThat(movement.isSatisfied(Color.WHITE, Position.of(1, 1), false)).isTrue(),
                () -> assertThat(movement.isSatisfied(Color.BLACK, Position.of(1, 1), false)).isFalse()
        );
    }

    @Test
    @DisplayName("이동은 가지고 있는 방향을 반환한다.")
    void getDirection() {
        UpRightDirection direction = new UpRightDirection(2);
        Movement movement = new Movement(new ColorPolicy(Color.WHITE), direction);
        assertThat(movement.getDirection()).isEqualTo(direction);
    }
}
