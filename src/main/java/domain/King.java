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
        return current.hasOnlyOneFileGap(target) ||
                current.hasOnlyOneRankGap(target) ||
                current.hasOnlyOneDiagonalGap(target);
    }
}
