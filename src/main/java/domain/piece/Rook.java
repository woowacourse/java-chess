package domain.piece;

public class Rook extends Piece {

    public Rook(final Color color) {
        super(color);
    }

    @Override
    public boolean findRoute() {
        return false;
    }
}
