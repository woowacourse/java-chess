package domain.piece;

import domain.game.Movement;
import domain.game.Position;
import domain.game.Score;
import domain.game.Side;

public class Bishop extends Piece {
    private static final Score SCORE = new Score(3);

    private Bishop(Side side) {
        super(side);
    }

    public static Bishop createOfWhite() {
        return new Bishop(Side.WHITE);
    }

    public static Bishop createOfBlack() {
        return new Bishop(Side.BLACK);
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

    public Score getScore() {
        return SCORE;
    }
}
