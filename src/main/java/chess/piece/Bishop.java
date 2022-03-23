package chess.piece;

public class Bishop extends AbstractPiece {

    public Bishop(final Color color) {
        super(color);
    }

    @Override
    public PieceType getPieceType() {
        return new PieceType(Name.BISHOP);
    }
}
