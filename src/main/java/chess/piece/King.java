package chess.piece;

public class King extends AbstractPiece {


    public King(final Color black) {
        super(black);
    }

    @Override
    public PieceType getPieceType() {
        return new PieceType(Name.KING);
    }
}
