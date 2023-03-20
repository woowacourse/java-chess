package chess.domain.piece;

import chess.domain.Team;
import chess.domain.movement.Movement;
import chess.domain.position.RelativePosition;

public class Pawn extends NoneEmptyPiece {

    private boolean hasMoved;

    public Pawn(final Team team) {
        super(team, Movement.PAWN);
        this.hasMoved = false;
    }

    @Override
    public boolean isMobile(RelativePosition relativePosition) {
        if (!hasMoved && isMoveTwoBlocks(relativePosition)) {
            relativePosition = relativePosition.toUnit();
        }
        if (isMovementMobile(relativePosition)) {
            hasMoved = true;
            return true;
        }
        return false;
    }

    private boolean isMoveTwoBlocks(RelativePosition relativePosition) {
        return relativePosition.equals(new RelativePosition(0, 2)) || relativePosition.equals(new RelativePosition(0, -2));
    }

    private boolean isMovementMobile(RelativePosition relativePosition) {
        if (team.isBlack()) {
            relativePosition = relativePosition.inverseByXAxis();
        }
        return movement.isMobile(relativePosition);
    }

    @Override
    public boolean isPawn() {
        return true;
    }

    @Override
    public PieceType getType() {
        return PieceType.PAWN;
    }
}
