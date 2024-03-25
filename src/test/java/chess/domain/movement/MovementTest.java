package chess.domain.movement;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.junit.jupiter.api.Assertions.assertAll;

import chess.domain.movement.direction.DownDirection;
import chess.domain.movement.direction.UpRightDirection;
import chess.domain.movement.policy.ColorPolicy;
import chess.domain.piece.Color;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MovementTest {

    @Test
    @DisplayName("이동은 정책과 방향을 가진다.")
    void Given_Movement_When_CreateMovementWithPolicyAndDirection_Then_DoesNotThrowAnyException() {
        //given, when, then
        assertThatCode(() -> new Movement(new ColorPolicy(Color.WHITE), new DownDirection(3)))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("이동은 가지고 있는 정책 조건이 만족하는지 확인한다.")
    void Given_Movement_When_IsSatisfiedPolicyWithPolicyAndDirection_Then_ReturnSamePolicy() {
        //given
        Movement movement = new Movement(new ColorPolicy(Color.WHITE), new UpRightDirection(2));
        //when, then
        assertAll(
                () -> assertThat(movement.isSatisfied(Color.WHITE, false, false)).isTrue(),
                () -> assertThat(movement.isSatisfied(Color.BLACK, false, false)).isFalse()
        );
    }

    @Test
    @DisplayName("이동은 가지고 있는 방향을 반환한다.")
    void Given_Movement_When_GetDirectionWithPolicyAndDirection_Then_ReturnSameDirection() {
        //given
        UpRightDirection direction = new UpRightDirection(2);
        Movement movement = new Movement(new ColorPolicy(Color.WHITE), direction);
        //when, then
        assertThat(movement.getDirection()).isEqualTo(direction);
    }
}
