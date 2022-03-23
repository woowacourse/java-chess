package chess.piece;

public class Rook extends AbstractPiece {

    public Rook(final Color color) {
        super(color);
    }

    @Override
    public PieceType getPieceType() {
        return new PieceType(Name.ROOK);
    }
}
