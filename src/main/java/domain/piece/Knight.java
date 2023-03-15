package domain.piece;

public class Knight extends Piece {

    public Knight(final Color color) {
        super(color);
    }

    @Override
    public boolean findRoute() {
        return false;
    }
}
