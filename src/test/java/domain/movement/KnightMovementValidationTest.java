package domain.movement;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

class KnightMovementValidationTest {
    @ParameterizedTest
    @EnumSource(value = Direction.class,
            names = {"KNIGHT_UP_LEFT", "KNIGHT_UP_RIGHT", "KNIGHT_LEFT_UP", "KNIGHT_LEFT_DOWN",
                    "KNIGHT_DOWN_LEFT", "KNIGHT_DOWN_RIGHT", "KNIGHT_RIGHT_UP", "KNIGHT_RIGHT_DOWN"})
    void 이동할_수_있는_방향이다(Direction direction) {
        MovementValidation knightMovementValidation = new KnightMovementValidation();

        assertThat(knightMovementValidation.isMovable(direction)).isTrue();
    }

    @ParameterizedTest
    @EnumSource(value = Direction.class,
            names = {"KNIGHT_UP_LEFT", "KNIGHT_UP_RIGHT", "KNIGHT_LEFT_UP", "KNIGHT_LEFT_DOWN",
            "KNIGHT_DOWN_LEFT", "KNIGHT_DOWN_RIGHT", "KNIGHT_RIGHT_UP", "KNIGHT_RIGHT_DOWN"},
            mode = EnumSource.Mode.EXCLUDE)
    void 이동할_수_없는_방향이다(Direction direction) {
        MovementValidation knightMovementValidation = new KnightMovementValidation();

        assertThat(knightMovementValidation.isMovable(direction)).isFalse();
    }

    @Test
    void 한_칸만_이동할_수_있다() {
        int moveCount = 1;
        MovementValidation knightMovementValidation = new KnightMovementValidation();

        assertThat(knightMovementValidation.isValidMoveCount(moveCount)).isTrue();
    }

    @Test
    void 두_칸_이상_이동할_수_없다() {
        int invalidMoveCount = 2;
        MovementValidation knightMovementValidation = new KnightMovementValidation();

        assertThat(knightMovementValidation.isValidMoveCount(invalidMoveCount)).isFalse();
    }
}
