package domain;

public class Bishop extends Piece {

    public Bishop(Side side) {
        super(side);
    }

    @Override
    public boolean isBishop() {
        return true;
    }

    @Override
    public boolean canMove(Position current, Position target) {
        return false;
    }
}
