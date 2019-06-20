package chess.domain.piece;

import chess.domain.MovementUnit;
import chess.domain.Spot;
import chess.domain.Team;

import java.util.HashSet;
import java.util.Set;

public class Knight extends Piece {
    private final Set<MovementUnit> movementUnits;

    public Knight(Team team) {
        super(team);
        movementUnits = new HashSet<>();
        movementUnits.add(MovementUnit.KNIGHT_MOVE_ONE);
        movementUnits.add(MovementUnit.KNIGHT_MOVE_TWO);
    }

    @Override
    public boolean isMovable(Spot startSpot, Spot endSpot) {
        int distanceX = startSpot.getX(endSpot);
        int distanceY = startSpot.getY(endSpot);

        return movementUnits.contains(MovementUnit.direction(distanceX, distanceY));
    }
}
