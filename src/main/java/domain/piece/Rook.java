package domain.piece;

import domain.game.PieceType;
import view.PieceCategory;

import java.util.Collections;
import java.util.List;

public class Rook extends Piece {
    private Rook(Side side) {
        super(side, PieceType.ROOK);
    }

    public static Rook createOfWhite() {
        return new Rook(Side.WHITE);
    }

    public static Rook createOfBlack() {
        return new Rook(Side.BLACK);
    }

    @Override
    public boolean isMovable(Piece targetPiece, Position sourcePosition, Position targetPosition) {
        Movement movement = sourcePosition.calculateMovement(targetPosition);
        if (this.isSameSideWith(targetPiece)) {
            return false;
        }
        return movement.isPerpendicular();
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
            return PieceCategory.WHITE_ROOK;
        }
        return PieceCategory.BLACK_ROOK;
    }
}
