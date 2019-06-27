package chess.domain.piece;

import chess.domain.MovementUnit;
import chess.domain.Spot;
import chess.domain.Team;

import java.util.Set;

public class Knight extends Piece {
    private static final double KNIGHT_SCORE = 2.5;

    private final Set<MovementUnit> movementUnits;

    public Knight(Team team) {
        super(team);
        this.score = KNIGHT_SCORE;
        pieceType = PieceType.KNIGHT;
        movementUnits = MovementUnit.getKnightWays();
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

