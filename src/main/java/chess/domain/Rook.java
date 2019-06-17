package chess.domain;

public class Rook implements Piece {

    private Rook() {

    }

    public static Rook getInstance() {
        return new Rook();
    }

    @Override
    public boolean isValidMove(final Position origin, final Position target) {
        return origin.isLevel(target) || origin.isPerpendicular(target);
    }
}
