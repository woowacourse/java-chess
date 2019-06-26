package chess.domain.piece;

import chess.domain.MovementUnit;
import chess.domain.Spot;
import chess.domain.Team;

import java.util.Set;

public class Queen extends Piece {
    private static final int QUEEN_SCORE = 9;

    private final Set<MovementUnit> movementUnits;

    public Queen(Team team) {
        super(team);
        this.score = QUEEN_SCORE;
        pieceType = PieceType.QUEEN;
        movementUnits = MovementUnit.getAllWay();
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
