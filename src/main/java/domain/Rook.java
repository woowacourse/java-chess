package domain;

public class Rook extends Piece {
    public Rook(Side side) {
        super(side);
    }

    @Override
    public boolean isRook() {
        return true;
    }

    @Override
    public boolean canMove(Position current, Position target) {
        return current.isSameRank(target) || current.isSameFile(target);
    }
}
