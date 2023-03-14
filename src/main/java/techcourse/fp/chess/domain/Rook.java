package techcourse.fp.chess.domain;

public class Rook extends Piece {

    public Rook(final Side side) {
        super(side);
    }

    @Override
    public boolean isMovable(final Position sourcePosition, final Position targetPosition) {
        if (sourcePosition.equals(targetPosition)) {
            return false;
        }

        return isSameFileOrder(sourcePosition, targetPosition) || isSameRankOrder(sourcePosition, targetPosition);
    }

    private boolean isSameRankOrder(final Position sourcePosition, final Position targetPosition) {
        return sourcePosition.getRankOrder() == targetPosition.getRankOrder();
    }

    private boolean isSameFileOrder(final Position sourcePosition, final Position targetPosition) {
        return sourcePosition.getFileOrder() == targetPosition.getFileOrder();
    }
}
