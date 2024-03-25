package chess.domain.movement;

import java.util.Set;

public class MovementsFactory {
    private MovementsFactory() {
    }

    public static Set<UnitMovement> createStraight() {
        return Set.of(UnitMovement.UP, UnitMovement.DOWN, UnitMovement.RIGHT, UnitMovement.LEFT);
    }

    public static Set<UnitMovement> createDiagonal() {
        return Set.of(UnitMovement.RIGHT_UP, UnitMovement.RIGHT_DOWN, UnitMovement.LEFT_UP, UnitMovement.LEFT_DOWN);
    }

    public static Set<UnitMovement> createKnightMovements() {
        return Set.of(
                UnitMovement.UP_UP_RIGHT, UnitMovement.UP_UP_LEFT,
                UnitMovement.DOWN_DOWN_RIGHT, UnitMovement.DOWN_DOWN_LEFT,
                UnitMovement.RIGHT_RIGHT_UP, UnitMovement.RIGHT_RIGHT_DOWN,
                UnitMovement.LEFT_LEFT_UP, UnitMovement.LEFT_LEFT_DOWN);
    }
}
