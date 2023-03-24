package domain.piece;

import domain.game.Movement;
import domain.game.Position;
import domain.game.Score;
import domain.game.Side;
import java.util.Collections;
import java.util.List;

public class King extends Piece {
    private static final Score SCORE = new Score(3);

    private King(Side side) {
        super(side);
    }

    public static King createOfWhite() {
        return new King(Side.WHITE);
    }

    public static King createOfBlack() {
        return new King(Side.BLACK);
    }

    @Override
    public boolean isMovable(Piece targetPiece, Position sourcePosition, Position targetPosition) {
        Movement movement = sourcePosition.calculateMovement(targetPosition);
        if (this.isSameSideWith(targetPiece)) {
            return false;
        }
        return movement.isOneStep();
    }

    @Override
    public List<Position> collectPath(Position sourcePosition, Position targetPosition) {
        return Collections.emptyList();
    }

    @Override
    public boolean isEmptyPiece() {
        return false;
    }

    @Override
    public PieceCategory getCategory() {
        if (side == Side.WHITE) {
            return PieceCategory.WHITE_KING;
        }
        return PieceCategory.BLACK_KING;
    }

    @Override
    public Score getScore() {
        return SCORE;
    }
}
