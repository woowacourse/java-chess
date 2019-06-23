package chess.domain.piece;

import chess.domain.MovementUnit;
import chess.domain.Spot;
import chess.domain.Team;

import java.util.HashSet;
import java.util.Set;

public class Pawn extends Piece {
    private static final Set<Integer> PAWN_START_COLUMN;

    static {
        PAWN_START_COLUMN = new HashSet<>();
        PAWN_START_COLUMN.add(1);
        PAWN_START_COLUMN.add(6);
    }

    private final Set<MovementUnit> movementUnits;
    private final Set<MovementUnit> attackUnits;
    private final int movingDirection;

    public Pawn(Team team) {
        super(team);
        this.score = 1;
        movingDirection = initDirection(team);
        movementUnits = new HashSet<>();
        attackUnits = new HashSet<>();
        movementUnits.add(MovementUnit.UP);
        attackUnits.add(MovementUnit.DIAGNOAL);
    }

    private int initDirection(Team team) {
        if (team == Team.BLACK) {
            return -1;
        }
        return 1;
    }

    //TODO 리팩토링
    @Override
    public boolean isMovable(Spot startSpot, Spot endSpot) {
        int distanceX = startSpot.getX(endSpot);
        int distanceY = startSpot.getY(endSpot);
        boolean isFirst = PAWN_START_COLUMN.stream().anyMatch(startSpot::checkColumn);
        int maxDistance = 1;
        if (isFirst) {
            maxDistance = 2;
        }
        if (movingDirection * distanceY > 0 && Math.abs(distanceY) <= maxDistance) {
            return movementUnits.contains(MovementUnit.direction(distanceX, distanceY));
        }
        return false;
    }

    @Override
    public boolean isAttackable(Spot startSpot, Spot endSpot) {
        int distanceX = startSpot.getX(endSpot);
        int distanceY = startSpot.getY(endSpot);

        if (distanceY * movingDirection == 1) {
            return attackUnits.contains(MovementUnit.direction(distanceX, distanceY));
        }
        return false;
    }
}
