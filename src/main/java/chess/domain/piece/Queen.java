package chess.domain.piece;

import chess.domain.MovementUnit;
import chess.domain.Spot;
import chess.domain.Team;

import java.util.HashSet;
import java.util.Set;

public class Queen extends Piece {
    private final Set<MovementUnit> movementUnits;

    public Queen(Team team) {
        super(team);
        this.score = 9;
        pieceType = PieceType.QUEEN;
        movementUnits = new HashSet<>();

        movementUnits.add(MovementUnit.UP);
        movementUnits.add(MovementUnit.DOWN);
        movementUnits.add(MovementUnit.RIGHT);
        movementUnits.add(MovementUnit.LEFT);

        movementUnits.add(MovementUnit.UP_RIGHT);
        movementUnits.add(MovementUnit.UP_LEFT);
        movementUnits.add(MovementUnit.DOWN_RIGHT);
        movementUnits.add(MovementUnit.DOWN_LEFT);
    }

    @Override
    public boolean isMovable(Spot startSpot, Spot endSpot) {
        MovementUnit movementUnit = startSpot.calculateMovement(endSpot);
        return movementUnits.contains(movementUnit);
    }

    @Override
    public boolean isAttackable(Spot startSpot, Spot endSpot) {
        return isMovable(startSpot, endSpot);
    }
}
