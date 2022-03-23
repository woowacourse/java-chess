package chess.piece;

public class Queen extends AbstractPiece {

    public Queen(final Color color) {
        super(color);
    }

    @Override
    public PieceType getPieceType() {
        return new PieceType(Name.QUEEN);
    }
}
