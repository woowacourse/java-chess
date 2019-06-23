package chess.domain.piece;

import chess.domain.MovementUnit;
import chess.domain.Spot;
import chess.domain.Team;

import java.util.HashSet;
import java.util.Set;

public class Rook extends Piece {
    private final Set<MovementUnit> movementUnits;

    public Rook(Team team) {
        super(team);
        this.score = 5;
        this.movementUnits = new HashSet<>();
        movementUnits.add(MovementUnit.UP);
        movementUnits.add(MovementUnit.RIGHT);
    }

    @Override
    public boolean isMovable(Spot startSpot, Spot endSpot) {
        int distanceX = startSpot.getX(endSpot);
        int distanceY = startSpot.getY(endSpot);

        return movementUnits.contains(MovementUnit.direction(distanceX, distanceY));
    }

    @Override
    public boolean isAttackable(Spot startSpot, Spot endSpot) {
        return isMovable(startSpot, endSpot);
    }
}
