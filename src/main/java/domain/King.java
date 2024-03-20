package domain;

public class King extends Piece {

    public King(Side side) {
        super(side);
    }

    @Override
    public boolean isKing() {
        return true;
    }

    @Override
    public boolean canMove(Position current, Position target) {
        return hasOnlyOneFileGap(current, target) ||
                hasOnlyOneRankGap(current, target) ||
                hasOnlyOneDiagonalGap(current, target);
    }

    private boolean hasOnlyOneFileGap(Position current, Position target) {
        return current.hasOneFileGap(target) &&
                current.isSameRank(target);
    }

    private boolean hasOnlyOneRankGap(Position current, Position target) {
        return current.hasOneRankGap(target) &&
                current.isSameFile(target);
    }

    private boolean hasOnlyOneDiagonalGap(Position current, Position target) {
        return current.isDiagonal(target) &&
                current.hasOneFileGap(target);
    }
}
