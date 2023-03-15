package chess.domain;

import java.util.List;

public abstract class Piece {

    private final boolean isWhite;
    private final boolean isFinite;
    private final List<Movement> movements;

    public Piece(boolean isWhite, boolean isFinite, List<Movement> movements) {
        this.isWhite = isWhite;
        this.isFinite = isFinite;
        this.movements = movements;
    }

    public boolean hasSameColor(Piece otherPiece) {
        return isWhite == otherPiece.isWhite;
    }

    public boolean hasMovement(Movement movement) {
        boolean hasMovement = false;
        for (Movement pieceMovement : movements) {
            hasMovement = hasMovement || compareMovement(pieceMovement, movement);
        }
        return hasMovement;
    }

    private boolean compareMovement(Movement pieceMovement, Movement movement) {
        if (isFinite) {
            return pieceMovement.equals(movement);
        }
        return pieceMovement.equals(movement.getUnitMovement());
    }
}
