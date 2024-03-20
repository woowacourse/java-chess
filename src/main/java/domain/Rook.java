package domain;

public class Rook extends Piece {

    public Rook(Side side) {
        super(side);
    }

    @Override
    public boolean isRook() {
        return true;
    }
}
