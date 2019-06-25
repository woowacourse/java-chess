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
        pieceType = PieceType.ROOK;
        this.movementUnits = new HashSet<>();

        movementUnits.add(MovementUnit.UP);
        movementUnits.add(MovementUnit.DOWN);
        movementUnits.add(MovementUnit.RIGHT);
        movementUnits.add(MovementUnit.LEFT);
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
