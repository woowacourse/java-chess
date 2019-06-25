package chess.domain.piece;

import chess.domain.MovementUnit;
import chess.domain.Spot;
import chess.domain.Team;

import java.util.HashSet;
import java.util.Set;

public class Bishop extends Piece {
    private final Set<MovementUnit> movementUnits;

    public Bishop(Team team) {
        super(team);
        this.score = 3;
        pieceType = PieceType.BISHOP;
        movementUnits = new HashSet<>();
        movementUnits.add(MovementUnit.UP_RIGHT);
        movementUnits.add(MovementUnit.UP_LEFT);
        movementUnits.add(MovementUnit.DOWN_RIGHT);
        movementUnits.add(MovementUnit.DOWN_LEFT);
    }

    @Override
    public boolean isMovable(Spot startSpot, Spot endSpot) {
        MovementUnit movement = startSpot.calculateMovement(endSpot);
        return movementUnits.contains(movement);
    }

    @Override
    public boolean isAttackable(Spot startSpot, Spot endSpot) {
        return isMovable(startSpot, endSpot);
    }
}
