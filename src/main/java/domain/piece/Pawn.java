package domain.piece;

public class Pawn extends Piece {

    public Pawn(final Color color) {
        super(color);
    }

    @Override
    public boolean findRoute() {
        return false;
    }
}
