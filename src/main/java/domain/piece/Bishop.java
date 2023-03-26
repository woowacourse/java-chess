package domain.piece;

import domain.game.PieceType;
import view.PieceCategory;

import java.util.Collections;
import java.util.List;

public class Bishop extends Piece {
    public Bishop(Side side) {
        super(side, PieceType.BISHOP);
    }

    @Override
    public boolean isMovable(Piece targetPiece, Position sourcePosition, Position targetPosition) {
        Movement movement = sourcePosition.calculateMovement(targetPosition);
        if (this.isSameSideWith(targetPiece)) {
            return false;
        }
        return movement.isDiagonal();
    }

    @Override
    public List<Position> collectPath(Position sourcePosition, Position targetPosition) {
        Movement movement = sourcePosition.calculateMovement(targetPosition);
        if (movement.isOneStep()) {
            return Collections.emptyList();
        }
        return sourcePosition.getPath(targetPosition);
    }

    @Override
    public boolean isEmptyPiece() {
        return false;
    }

    @Override
    public PieceCategory getCategory() {
        if (side == Side.WHITE) {
            return PieceCategory.WHITE_BISHOP;
        }
        return PieceCategory.BLACK_BISHOP;
    }
}
