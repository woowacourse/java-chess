package chess.domain.piece;

import chess.domain.position.Position;
import java.util.Collection;

public class Pawn extends Piece {

    private static final int ONE_SQUARE = 1;
    private static final int DOUBLE_STEP = 2;
    private static final int SINGLE_STEP = 1;
    private static final int PAWN_SCORE = 1;

    public Pawn(Color color, Position position) {
        super(color, position);
    }

    @Override
    protected Piece createNewPiece(Position to) {
        return new Pawn(getColor(), to);
    }

    @Override
    protected boolean isPossibleMovement(Position to, Collection<Piece> pieces) {
        if (!isForward(to)) {
            return false;
        }
        if (getPosition().isVerticalWay(to) && isValidDistance(to)) {
            return pieces.stream()
                .noneMatch(piece -> piece.isSamePosition(to));
        }
        if (getPosition().isDiagonalWay(to)
            && getPosition().getVerticalDistance(to) == ONE_SQUARE) {
            return pieces.stream()
                .anyMatch(piece -> piece.isSamePosition(to));
        }
        return false;
    }

    private boolean isForward(Position to) {
        return getColor().isForward(getPosition(), to);
    }

    private boolean isValidDistance(Position to) {
        return getPosition().getVerticalDistance(to) <= movableDistance();
    }

    private int movableDistance() {
        if (isStartPawnPosition()) {
            return DOUBLE_STEP;
        }
        return SINGLE_STEP;
    }

    private boolean isStartPawnPosition() {
        return getColor().isStartPawnPosition(getPosition());
    }

    @Override
    public double score() {
        return PAWN_SCORE;
    }

    @Override
    public boolean isPawn() {
        return true;
    }
}
