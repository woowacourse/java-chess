package domain.piece;

public class Bishop extends Piece {

    public Bishop(final Color color) {
        super(color);
    }

    @Override
    public boolean findRoute() {
        return false;
    }
}
