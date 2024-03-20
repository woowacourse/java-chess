package domain;

public class Knight extends Piece {

    public Knight(Side side) {
        super(side);
    }

    @Override
    public boolean isKnight() {
        return true;
    }
}
