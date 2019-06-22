package chess.domain.piece;

import chess.domain.MovementUnit;
import chess.domain.Spot;
import chess.domain.Team;

import java.util.HashSet;
import java.util.Set;

public class Pawn extends Piece {
    private final Set<MovementUnit> movementUnits;
    private final int movingDirection;
    private boolean isFirst;

    Pawn(Team team) {
        super(team);
        movingDirection = initDirection(team);
        isFirst = true;
        movementUnits = new HashSet<>();
        movementUnits.add(MovementUnit.DIAGNOAL);
        movementUnits.add(MovementUnit.UP);
    }

    private int initDirection(Team team) {
        if (team == Team.EMPTY) {
            return 1;
        }
        return -1;
    }

    @Override
    public boolean isMovable(Spot startSpot, Spot endSpot) {
        return false;
    }

    @Override
    public boolean isAttackable(Spot startSpot, Spot endSpot) {
        return false;
    }
}
