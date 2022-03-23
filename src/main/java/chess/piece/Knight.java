package chess.piece;

public class Knight extends AbstractPiece {

    public Knight(final Color color) {
        super(color);
    }

    @Override
    public PieceType getPieceType() {
        return new PieceType(Name.KNIGHT);
    }
}
