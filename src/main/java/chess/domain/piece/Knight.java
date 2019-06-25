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
        this.score = 2.5;
        pieceType = PieceType.KNIGHT;
        movementUnits = new HashSet<>();

        movementUnits.add(MovementUnit.KNIGHT_UP_RIGHT);
        movementUnits.add(MovementUnit.KNIGHT_RIGHT_UP);
        movementUnits.add(MovementUnit.KNIGHT_UP_LEFT);
        movementUnits.add(MovementUnit.KNIGHT_LEFT_UP);
        movementUnits.add(MovementUnit.KNIGHT_DOWN_RIGHT);
        movementUnits.add(MovementUnit.KNIGHT_RIGHT_DOWN);
        movementUnits.add(MovementUnit.KNIGHT_DOWN_LEFT);
        movementUnits.add(MovementUnit.KNIGHT_LEFT_DOWN);
    }

    @Override
    public boolean isMovable(Spot startSpot, Spot endSpot) {
        int xGap = startSpot.xGap(endSpot);
        int yGap = startSpot.yGap(endSpot);

        return movementUnits.contains(MovementUnit.direction(xGap, yGap));
    }

    @Override
    public boolean isAttackable(Spot startSpot, Spot endSpot) {
        return isMovable(startSpot, endSpot);
    }
}
