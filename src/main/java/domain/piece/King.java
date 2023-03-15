package domain.piece;

public class King extends Piece {

    public King(final Color color) {
        super(color);
    }

    @Override
    public boolean findRoute() {
        return false;
    }

}
