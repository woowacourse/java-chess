package domain;

public class Queen extends Piece {

    public Queen(Side side) {
        super(side);
    }

    @Override
    public boolean isQueen() {
        return true;
    }
}
