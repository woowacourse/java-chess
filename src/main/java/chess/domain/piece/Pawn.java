package chess.domain.piece;

import chess.domain.Team;
import chess.domain.movement.Movement;
import chess.domain.position.RelativePosition;

public class Pawn extends NoneEmptyPiece {

    private boolean hasMoved;

    private Pawn(PieceType pieceType, Team team, Movement movement, boolean hasMoved) {
        super(pieceType, team, movement);
        this.hasMoved = hasMoved;
    }

    public static Pawn from(Team team){
        return new Pawn(PieceType.PAWN, team, Movement.PAWN, false);
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
}
