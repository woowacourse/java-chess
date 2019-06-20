package chess.domain.piece;

import chess.domain.MovementUnit;
import chess.domain.Spot;
import chess.domain.Team;

import java.util.HashSet;
import java.util.Set;

public class King extends Piece {
    private static final int KING_MOVING_CONDITION = 4;

    private final Set<MovementUnit> movementUnits;

    King(Team team) {
        super(team);
        movementUnits = new HashSet<>();
        movementUnits.add(MovementUnit.RIGHT);
        movementUnits.add(MovementUnit.UP);
        movementUnits.add(MovementUnit.DIAGNOAL);
    }

    @Override
    public boolean isMovable(Spot startSpot, Spot endSpot) {
        int distanceX = startSpot.getX(endSpot);
        int distanceY = startSpot.getY(endSpot);
        int distance = getDistance(distanceX, distanceY);

        if (distance < KING_MOVING_CONDITION) {
            return movementUnits.contains(MovementUnit.direction(distanceX, distanceY));
        }
        return false;
    }

    private int getDistance(int distanceX, int distanceY) {
        return distanceX * distanceX + distanceY * distanceY;
    }
}
