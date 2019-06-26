package chess.domain.piece;

import chess.domain.MovementUnit;
import chess.domain.Spot;
import chess.domain.Team;

import java.util.Set;

public class Rook extends Piece {
    private static final int ROOK_SCORE = 5;

    private final Set<MovementUnit> movementUnits;

    public Rook(Team team) {
        super(team);
        this.score = ROOK_SCORE;
        pieceType = PieceType.ROOK;
        this.movementUnits = MovementUnit.getFourWay();
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
