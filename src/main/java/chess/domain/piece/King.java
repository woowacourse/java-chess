package chess.domain.piece;

import chess.domain.MovementUnit;
import chess.domain.Spot;
import chess.domain.Team;

import java.util.HashSet;
import java.util.Set;

public class King extends Piece {
    private static final int KING_MOVING_CONDITION = 4;

    private final Set<MovementUnit> movementUnits;

    public King(Team team) {
        super(team);
        pieceType = PieceType.KING;
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
        int xGap = startSpot.xGap(endSpot);
        int yGap = startSpot.yGap(endSpot);

        if (validMove(xGap, yGap)) {
            MovementUnit movement = startSpot.calculateMovement(endSpot);
            return movementUnits.contains(movement);
        }

        return false;

    }

    private boolean validMove(int xGap, int yGap) {
        return (Math.pow(xGap, 2) + Math.pow(yGap, 2)) <= 4;
    }

    @Override
    public boolean isAttackable(Spot startSpot, Spot endSpot) {
        return isMovable(startSpot, endSpot);
    }
}
