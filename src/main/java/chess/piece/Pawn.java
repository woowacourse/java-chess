package chess.piece;

public class Pawn extends AbstractPiece {

    public Pawn(final Color color) {
        super(color);
    }

    @Override
    public PieceType getPieceType() {
        return new PieceType(Name.PAWN);
    }
}
