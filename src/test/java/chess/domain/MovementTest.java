package chess.domain;

import static chess.domain.Direction.DOWN;
import static chess.domain.Direction.LEFT;
import static chess.domain.Direction.RIGHT;
import static chess.domain.Direction.UP;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class MovementTest extends AbstractTestFixture {

    @DisplayName("상하 대칭 할 수 있다.")
    @Test
    void flipHorizontal() {
        Movement movement = createMovement(RIGHT, UP, UP);
        Movement movement2 = createMovement(RIGHT, DOWN, DOWN);

        assertThat(movement2.isSameWith(movement.flipHorizontal())).isTrue();
    }

    @DisplayName("좌우 대칭 할 수 있다.")
    @Test
    void flipVertical() {
        Movement movement = createMovement(RIGHT, RIGHT, UP);
        Movement movement2 = createMovement(LEFT, LEFT, UP);

        assertThat(movement2.isSameWith(movement.flipVertical())).isTrue();
    }

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

    @DisplayName("같은 움직임인지 확인한다")
    @Test
    void isSameWith() {
        Movement movement1 = createMovement(RIGHT, UP, RIGHT);
        Movement movement2 = createMovement(RIGHT, RIGHT, UP);

        assertThat(movement1.isSameWith(movement2)).isTrue();
    }

    @DisplayName("다른 움직임인지 확인한다")
    @Test
    void isNotSameWith() {
        Movement movement1 = createMovement(RIGHT, UP);
        Movement movement2 = createMovement(RIGHT, RIGHT, UP, UP);

        assertThat(movement1.isSameWith(movement2)).isFalse();
    }

    @DisplayName("목적지를 찾을 수 있다")
    @Test
    void findDestination() {
        Movement movement = createMovement(RIGHT, RIGHT, UP, UP, UP);
        Position destination = movement.findDestination(createPosition("A,ONE"));

        assertThat(destination).isEqualTo(createPosition("C,FOUR"));
    }
}
