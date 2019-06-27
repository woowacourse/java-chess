package chess.domain.piece;

import chess.domain.MovementUnit;
import chess.domain.Spot;
import chess.domain.Team;

import java.util.Set;

public class King extends Piece {
    private final Set<MovementUnit> movementUnits;

    public King(Team team) {
        super(team);
        pieceType = PieceType.KING;
        movementUnits = MovementUnit.getAllWay();
    }

    @Override
    public boolean isMovable(Spot startSpot, Spot endSpot) {
        int xGap = startSpot.xGap(endSpot);
        int yGap = startSpot.yGap(endSpot);

        if (validDistance(xGap, yGap)) {
            MovementUnit movement = startSpot.calculateMovement(endSpot);
            return movementUnits.contains(movement);
        }

        return false;

    }

    private boolean validDistance(int xGap, int yGap) {
        return (Math.pow(xGap, 2) + Math.pow(yGap, 2)) < 4;
    }

    @Override
    public boolean isAttackable(Spot startSpot, Spot endSpot) {
        return isMovable(startSpot, endSpot);
    }
}

