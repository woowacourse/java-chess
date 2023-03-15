package domain.piece;

public class Queen extends Piece {

    public Queen(final Color color) {
        super(color);
    }

    @Override
    public boolean findRoute() {
        return false;
    }
}
