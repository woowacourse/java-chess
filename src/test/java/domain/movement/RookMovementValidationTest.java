package domain.movement;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.ValueSource;

class RookMovementValidationTest {
    @ParameterizedTest
    @EnumSource(value = Direction.class, names = {"UP", "DOWN", "LEFT", "RIGHT"})
    void 이동할_수_있는_방향이다(Direction direction) {
        MovementValidation rookMovementValidation = new RookMovementValidation();

        assertThat(rookMovementValidation.isMovable(direction)).isTrue();
    }

    @ParameterizedTest
    @EnumSource(value = Direction.class,names = {"UP", "DOWN", "LEFT", "RIGHT"}, mode = EnumSource.Mode.EXCLUDE)
    void 이동할_수_없는_방향이다(Direction direction) {
        MovementValidation rookMovementValidation = new RookMovementValidation();

        assertThat(rookMovementValidation.isMovable(direction)).isFalse();
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 8})
    void 몇_칸이든_이동할_수_있다(int moveCount) {
        MovementValidation rookMovementValidation = new RookMovementValidation();

        assertThat(rookMovementValidation.isValidMoveCount(moveCount)).isTrue();
    }
}
