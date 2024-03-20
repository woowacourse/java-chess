package domain;

public class Pawn extends Piece {

    public Pawn(Side side) {
        super(side);
    }

    @Override
    public boolean isPawn() {
        return true;
    }
}
