package domain.piece;

import java.util.Collections;
import java.util.List;

public class Queen extends Piece {
    private Queen(Side side) {
        super(side);
    }

    public static Queen createOfWhite() {
        return new Queen(Side.WHITE);
    }

    public static Queen createOfBlack() {
        return new Queen(Side.BLACK);
    }

    @Override
    public boolean isMovable(Piece targetPiece, Position sourcePosition, Position targetPosition) {
        Movement movement = sourcePosition.calculateMovement(targetPosition);
        if (this.isSameSideWith(targetPiece)) {
            return false;
        }
        return movement.isDiagonal() || movement.isPerpendicular();
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
            return PieceCategory.WHITE_QUEEN;
        }
        return PieceCategory.BLACK_QUEEN;
    }
}
