package domain.movement;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.ValueSource;

class BishopMovementValidationTest {
    @ParameterizedTest
    @EnumSource(value = Direction.class, names = {"UP_LEFT", "UP_RIGHT", "DOWN_LEFT", "DOWN_RIGHT"})
    void 이동할_수_있는_방향이다(Direction direction) {
        MovementValidation bishopMovementValidation = new BishopMovementValidation();

        assertThat(bishopMovementValidation.isMovable(direction)).isTrue();
    }

    @ParameterizedTest
    @EnumSource(value = Direction.class,
            names = {"UP_LEFT", "UP_RIGHT", "DOWN_LEFT", "DOWN_RIGHT"},
            mode = EnumSource.Mode.EXCLUDE)
    void 이동할_수_없는_방향이다(Direction direction) {
        MovementValidation bishopMovementValidation = new BishopMovementValidation();

        assertThat(bishopMovementValidation.isMovable(direction)).isFalse();
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 8})
    void 몇_칸이든_이동할_수_있다(int moveCount) {
        MovementValidation bishopMovementValidation = new BishopMovementValidation();

        assertThat(bishopMovementValidation.isValidMoveCount(moveCount)).isTrue();
    }
}
