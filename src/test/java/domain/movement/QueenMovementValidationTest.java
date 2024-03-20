package domain.movement;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.ValueSource;

class QueenMovementValidationTest {
    @ParameterizedTest
    @EnumSource(value = Direction.class, names = {"UP", "DOWN", "LEFT", "RIGHT", "UP_LEFT", "UP_RIGHT", "DOWN_LEFT",
            "DOWN_RIGHT"})
    void 이동할_수_있는_방향이다(Direction direction) {
        QueenMovementValidation queenMovementValidation = new QueenMovementValidation();

        assertTrue(queenMovementValidation.isMovable(direction));
    }

    @ParameterizedTest
    @EnumSource(value = Direction.class, names = {"UP", "DOWN", "LEFT", "RIGHT", "UP_LEFT", "UP_RIGHT", "DOWN_LEFT",
            "DOWN_RIGHT"}, mode = EnumSource.Mode.EXCLUDE)
    void 이동할_수_없는_방향이다(Direction direction) {
        QueenMovementValidation queenMovementValidation = new QueenMovementValidation();

        assertFalse(queenMovementValidation.isMovable(direction));
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 8})
    void 몇_칸이든_이동할_수_있다(int moveCount) {
        QueenMovementValidation queenMovementValidation = new QueenMovementValidation();

        assertThat(queenMovementValidation.isValidMoveCount(moveCount)).isTrue();
    }


}
