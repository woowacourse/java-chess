package chess;

public abstract class AbstractPiece implements Piece {

    private final Position position;

    AbstractPiece(final Position position) {
        this.position = position;
    }

    @Override
    public Position getPosition() {
        return position;
    }
}
