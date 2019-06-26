package chess.domain.piece;

import chess.domain.MovementUnit;
import chess.domain.Spot;
import chess.domain.Team;

import java.util.Set;

public class Bishop extends Piece {
    private static final int BISHOP_SCORE = 3;

    private final Set<MovementUnit> movementUnits;

    public Bishop(Team team) {
        super(team);
        this.score = BISHOP_SCORE;
        pieceType = PieceType.BISHOP;
        movementUnits = MovementUnit.getDiagonals();
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
