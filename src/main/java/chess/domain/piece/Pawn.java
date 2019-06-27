package chess.domain.piece;

import chess.domain.MovementUnit;
import chess.domain.Spot;
import chess.domain.Team;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Pawn extends Piece {
    public static final int PAWN_SCORE = 1;
    private static final int PAWN_X_MAXIMUM_MOVABLE_DISTANCE = 1;
    private static final int PAWN_X_MINIMUM_MOVABLE_DISTANCE = 0;
    private static final int PAWN_Y_MAXIMUM_MOVABLE_DISTANCE = 2;
    private static final int PAWN_Y_MINIMUM_MOVABLE_DISTANCE = 1;
    private static final Set<Integer> PAWN_START_COLUMN;

    static {
        PAWN_START_COLUMN = new HashSet<>();
        PAWN_START_COLUMN.add(1);
        PAWN_START_COLUMN.add(6);
    }

    private final Set<MovementUnit> movementUnits;
    private final Set<MovementUnit> attackUnits;

    public Pawn(Team team) {
        super(team);
        this.score = PAWN_SCORE;
        pieceType = PieceType.PAWN;
        movementUnits = new HashSet<>();
        attackUnits = new HashSet<>();

        if (team == Team.WHITE) {
            movementUnits.add(MovementUnit.UP);
            attackUnits.add(MovementUnit.UP_LEFT);
            attackUnits.add(MovementUnit.UP_RIGHT);
        }

        if (team == Team.BLACK) {
            movementUnits.add(MovementUnit.DOWN);
            attackUnits.add(MovementUnit.DOWN_LEFT);
            attackUnits.add(MovementUnit.DOWN_RIGHT);
        }
    }

    @Override
    public boolean isMovable(Spot startSpot, Spot endSpot) {
        MovementUnit movementUnit = endSpot.calculateMovement(startSpot);

        if (!movementUnits.contains(movementUnit)) {
            return false;
        }

        int xGap = Math.abs(startSpot.xGap(endSpot));
        int yGap = Math.abs(startSpot.yGap(endSpot));

        if (startSpot.isStartLine()) {
            return xGap == PAWN_X_MINIMUM_MOVABLE_DISTANCE
                    && yGap <= PAWN_Y_MAXIMUM_MOVABLE_DISTANCE;
        }

        if (!startSpot.isStartLine()) {
            return xGap == PAWN_X_MINIMUM_MOVABLE_DISTANCE
                    && yGap == PAWN_Y_MINIMUM_MOVABLE_DISTANCE;
        }

        return false;
    }

    @Override
    public boolean isAttackable(Spot startSpot, Spot endSpot) {
        MovementUnit attackUnit = endSpot.calculateMovement(startSpot);

        if (!attackUnits.contains(attackUnit)) {
            return false;
        }

        int xGap = Math.abs(startSpot.xGap(endSpot));
        int yGap = Math.abs(startSpot.yGap(endSpot));

        return xGap == PAWN_X_MAXIMUM_MOVABLE_DISTANCE
                && yGap == PAWN_Y_MINIMUM_MOVABLE_DISTANCE;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Pawn pawn = (Pawn) o;
        return Objects.equals(movementUnits, pawn.movementUnits) &&
                Objects.equals(attackUnits, pawn.attackUnits);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), movementUnits, attackUnits);
    }
}
