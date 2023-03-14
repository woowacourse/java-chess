package chess.domain;

import static chess.domain.Direction.LEFT;
import static chess.domain.Direction.RIGHT;
import static chess.domain.Direction.UP;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class MovementTest extends AbstractTestFixture {

    @DisplayName("기울기가 같은지 알 수 있다")
    @Test
    void isSameAngle() {
        Movement movement = createMovement(RIGHT, UP);
        Movement movement2 = createMovement(RIGHT, RIGHT, UP, UP);

        assertThat(movement.isSameAngle(movement2)).isTrue();
    }

    @DisplayName("기울기가 다른지 알 수 있다")
    @Test
    void isNotSameAngle() {
        Movement movement = createMovement(RIGHT, UP);
        Movement movement2 = createMovement(RIGHT, RIGHT, UP);

        assertThat(movement.isSameAngle(movement2)).isFalse();
    }

    @DisplayName("빈 움직임끼리는 기울기가 같다")
    @Test
    void isSameAngle_noDirections() {
        Movement movement = createMovement();
        Movement movement2 = createMovement();

        assertThat(movement.isSameAngle(movement2)).isTrue();
    }

    @DisplayName("양방향이 존재하면 예외를 던진다")
    @Test
    void bidirectional_throws() {
        assertThatThrownBy(() -> createMovement(RIGHT, LEFT))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("양방향이 존재하면 안됩니다");
    }
}
